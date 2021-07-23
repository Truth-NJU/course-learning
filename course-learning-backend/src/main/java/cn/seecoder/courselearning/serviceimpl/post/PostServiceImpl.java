package cn.seecoder.courselearning.serviceimpl.post;

import cn.seecoder.courselearning.dto.post.PostDTO;
import cn.seecoder.courselearning.dto.post.ReplyDTO;
import cn.seecoder.courselearning.enums.HttpStatus;
import cn.seecoder.courselearning.mapperservice.post.PostMapper;
import cn.seecoder.courselearning.mapperservice.post.ReplyMapper;
import cn.seecoder.courselearning.po.post.PostPO;
import cn.seecoder.courselearning.po.post.ReplyPO;
import cn.seecoder.courselearning.service.post.PostService;
import cn.seecoder.courselearning.util.HandlePageInfo;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.post.PostVO;
import cn.seecoder.courselearning.vo.post.ReplyVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


//Unit Test Has Finished
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    public PostMapper postMapper;

    @Autowired
    public ReplyMapper replyMapper;


    public ResultVO<PostVO> createPost(PostDTO postDTO) {
        PostPO postPO = new PostPO(postDTO);
        if (!postPO.isFull()) {
            return ResultVO.fail(HttpStatus.DATA_INTEGRALITY_ERROR);
        }
        postMapper.addPost(postPO);
        return new ResultVO<>(new PostVO(postPO));
    }


    /**
     * 1.删除帖子
     * 2.找到帖子下所有回复，以队列的形式加入回复List
     * 3.查找所有回复的回复加入回复List
     * 4.删除回复List中全部回复
     *
     * @param postDTO
     * @return
     */
    public ResultVO deletePost(PostDTO postDTO) {
        //删除帖子
        if (postMapper.deletePost(postDTO.getPostId()) == 1) {
            //找到帖子下所有回复，以队列的形式加入回复List
            System.err.println(postDTO.getPostId());
            List<ReplyPO> origin = replyMapper.selectReplyByPostId(postDTO.getPostId());
            List<ReplyPO> replyPOList = new ArrayList<>(origin);
            //查找所有回复的回复加入回复List
            if (replyPOList.size() != 0) {
                for (int i = 0; i < replyPOList.size(); i++) {
                    List<ReplyPO> replyToReplyList = replyMapper.selectReplyByRepliedId(replyPOList.get(i).getReplyId());
                    if (replyToReplyList != null && replyToReplyList.size() != 0) {
                        replyPOList.addAll(replyToReplyList);
                    }
                }
                //删除回复List中全部回复
                for (ReplyPO replyPO : replyPOList) {
                    replyMapper.deleteReply(replyPO.getReplyId());
                }
            }
            return new ResultVO<>(1, "操作成功");
        } else {
            return new ResultVO<>(0, "操作失败");
        }
    }

    public PageInfo<PostVO> searchPosts(int courseId, int pageNo, int number) {
        PageHelper.startPage(pageNo, number);
        List<PostPO> postPOS = postMapper.selectPostByCourseId(courseId);
        PageInfo pageInfoPO = new PageInfo(postPOS);
        List<PostVO> postVOS = new ArrayList<>();
        for (int i = 0; i < postPOS.size(); i++) {
            postVOS.add(new PostVO(postPOS.get(i)));
        }
        PageInfo pageInfo = new PageInfo();
        BeanUtils.copyProperties(pageInfoPO, pageInfo);
        pageInfo.setList(postVOS);
        return pageInfo;
    }


    /**
     * 1.查询帖子的所有回复
     * 2.如果是帖子发布者，将分页结果中的所有回复置为已阅
     *
     * @param postId
     * @param pageNo
     * @param number
     * @param userId
     * @return
     */
    public PageInfo<ReplyVO> searchReplies(int postId, int pageNo, int number, Integer userId) {
        PageHelper.startPage(pageNo, number);
        List<ReplyPO> replyPOS = replyMapper.selectReplyByPostId(postId);
        PageInfo pageInfoPO = new PageInfo(replyPOS);
        List<ReplyVO> replyVOS = new ArrayList<>();

        //判断是否为发布者
        boolean isPoster = null != userId && postMapper.selectPostByPostId(postId).getUserId() == userId;

        for (ReplyPO replyPO : replyPOS) {
            //如果是帖子发布者，则将分页结果中的所有回复置为已阅
            if (isPoster && !replyPO.isLooked()) {
                replyMapper.updateStatus(replyPO.getReplyId());
            }
            if (!isPoster) {
                replyPO.setStatus(null);
            }
            replyVOS.add(new ReplyVO(replyPO));
        }
        PageInfo pageInfo = new PageInfo();
        BeanUtils.copyProperties(pageInfoPO, pageInfo);
        pageInfo.setList(replyVOS);
        return pageInfo;
    }


    public ResultVO<ReplyVO> createReply(ReplyDTO replyDTO) {
        ReplyPO replyPO = new ReplyPO(replyDTO);
        if (!replyPO.isFull()) {
            return ResultVO.fail(HttpStatus.DATA_INTEGRALITY_ERROR);
        }
        replyMapper.reply(replyPO);
        return new ResultVO<>(new ReplyVO(replyPO));

    }


    public ResultVO<ReplyVO> createReply2Reply(ReplyDTO replyDTO) {
        ReplyPO replyPO = new ReplyPO(replyDTO);
        if (!replyPO.isFull()) {
            return ResultVO.fail(HttpStatus.DATA_INTEGRALITY_ERROR);
        }
        replyMapper.replyReply(replyPO);
        return new ResultVO<>(new ReplyVO(replyPO));
    }


    /**
     * 1.查询回复的全部回复
     * 2.如果是回复发布者，则将分页结果中的所有回复置为已阅
     *
     * @param repliedId
     * @param pageNo
     * @param number
     * @param userId
     * @return
     */
    public PageInfo<ReplyVO> searchReply2Reply(int repliedId, int pageNo, int number, Integer userId) {
        //查询回复的全部回复
        PageHelper.startPage(pageNo, number);
        List<ReplyPO> replyPOS = replyMapper.selectReplyByRepliedId(repliedId);
        PageInfo pageInfoPO = new PageInfo(replyPOS);
        List<ReplyVO> replyVOS = new ArrayList<>();

        //判断是否为发布者
        boolean isPoster = null != userId && replyMapper.selectReplyByReplyId(repliedId).getReplyUserId().equals(userId);

        for (ReplyPO replyPO : replyPOS) {
            //如果是回复发布者，则将分页结果中的所有回复置为已阅
            if (isPoster && !replyPO.isLooked()) {
                replyMapper.updateStatus(replyPO.getReplyId());
            }
            replyVOS.add(new ReplyVO(replyPO));
        }
        PageInfo pageInfo = new PageInfo();
        BeanUtils.copyProperties(pageInfoPO, pageInfo);
        pageInfo.setList(replyVOS);
        return pageInfo;
    }


    /**
     * 1.查询分页的所有帖子
     * 2.查询所有帖子的回复，返回有多少新回复以及新回复的id
     *
     * @param userId
     * @param pageNo
     * @param number
     * @return
     */
    public PageInfo<PostVO> searchUserPosts(int userId, int pageNo, int number) {
        //查询该分页的所有帖子
        PageHelper.startPage(pageNo, number);
        List<PostPO> postPOS = postMapper.selectPostByUserId(userId);

        PageInfo pageInfoPO = new PageInfo(postPOS);
        List<PostVO> postVOS = new ArrayList<>();
        for (PostPO postPO : postPOS) {
            //查询所有帖子的回复，返回有多少新回复，以及新回复的id
            int newReplyNum = 0;
            List<Integer> newReplyList = new ArrayList<>();
            for (ReplyPO replyPO : replyMapper.selectReplyByPostId(postPO.getPostId())) {
                if (!replyPO.isLooked()) {
                    newReplyNum++;
                    newReplyList.add(replyPO.getReplyId());
                }
            }
            PostVO postVO = new PostVO(postPO);
            postVO.setNewReplyNum(newReplyNum);
            postVO.setNewReplyList(newReplyList);

            postVOS.add(postVO);
        }
        PageInfo pageInfo = new PageInfo();
        BeanUtils.copyProperties(pageInfoPO, pageInfo);
        pageInfo.setList(postVOS);
        return pageInfo;
    }


    /**
     * 1.查询所有回复
     * 2.查询所有回复的一级子回复，返回有多少新回复以及新回复的id
     *
     * @param userId
     * @param pageNo
     * @param number
     * @return
     */
    public PageInfo<ReplyVO> searchUserReplies(int userId, int pageNo, int number) {
        //查询所有回复
        PageHelper.startPage(pageNo, number);
        List<ReplyPO> replyPOS = replyMapper.selectReplyByUserId(userId);

        PageInfo pageInfoPO = new PageInfo(replyPOS);
        List<ReplyVO> replyVOS = new ArrayList<>();
        for (ReplyPO replyPO : replyPOS) {
            //查询所有回复的回复，返回有多少新回复，以及新回复的id
            int newReplyNum = 0;
            List<Integer> newReplyList = new ArrayList<>();
            for (ReplyPO reply2replyPO : replyMapper.selectReplyByRepliedId(replyPO.getReplyId())) {
                if (!reply2replyPO.isLooked()) {
                    newReplyNum++;
                    newReplyList.add(reply2replyPO.getReplyId());
                }
            }
            ReplyVO replyVO = new ReplyVO(replyPO);
            replyVO.setNewReplyList(newReplyList);
            replyVO.setNewReplyNum(newReplyNum);

            replyVOS.add(replyVO);
        }
        PageInfo pageInfo = new PageInfo();
        BeanUtils.copyProperties(pageInfoPO, pageInfo);
        pageInfo.setList(replyVOS);
        return pageInfo;
    }

    /**
     * 找到所有reply，然后分页返回
     * 将分页结果的reply更新为已读
     *
     * @param replyIdList
     * @param pageNo
     * @param number
     * @return
     */
    @Override
    public PageInfo<ReplyVO> searchNewReplies(List<Integer> replyIdList, int pageNo, int number) {
        //找到所有reply，然后分页返回
        List<ReplyVO> replyVOList = new ArrayList<>();
        for (Integer replyId : replyIdList) {
            replyVOList.add(new ReplyVO(replyMapper.selectReplyByReplyId(replyId)));
        }
        PageInfo<ReplyVO> replyVOPageInfo = HandlePageInfo.list2PageInfo(replyVOList, pageNo, number);
        //将分页结果的reply更新为已读
        for (ReplyVO replyVO : replyVOPageInfo.getList()) {
            replyMapper.updateStatus(replyVO.getReplyId());
        }
        return replyVOPageInfo;
    }

}
