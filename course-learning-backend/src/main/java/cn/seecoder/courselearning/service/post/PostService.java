package cn.seecoder.courselearning.service.post;

import cn.seecoder.courselearning.dto.post.PostDTO;
import cn.seecoder.courselearning.dto.post.ReplyDTO;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.post.PostVO;
import cn.seecoder.courselearning.vo.post.ReplyVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PostService {

    ResultVO<PostVO> createPost(PostDTO postDTO);

    ResultVO deletePost(PostDTO postDTO);


    PageInfo<PostVO> searchPosts(int courseId, int pageNo, int number);

    PageInfo<ReplyVO> searchReplies(int postId, int pageNo, int number, Integer userId);

    ResultVO<ReplyVO> createReply(ReplyDTO replyDTO);

    ResultVO<ReplyVO> createReply2Reply(ReplyDTO replyDTO);

    PageInfo<ReplyVO> searchReply2Reply(int repliedId, int pageNo, int number, Integer userId);

    PageInfo<PostVO> searchUserPosts(int userId, int pageNo, int number);

    PageInfo<ReplyVO> searchUserReplies(int userId, int pageNo, int number);

    PageInfo<ReplyVO> searchNewReplies(List<Integer> replyIdList, int pageNo, int number);
}
