package cn.seecoder.courselearning.controller.post;

import cn.seecoder.courselearning.dto.post.PostDTO;
import cn.seecoder.courselearning.dto.post.ReplyDTO;
import cn.seecoder.courselearning.service.post.PostService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.post.PostVO;
import cn.seecoder.courselearning.vo.post.ReplyVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * 为一门课创建一个帖子
     *
     * @param postDTO
     * @return
     */
    @PostMapping("/create")
    public ResultVO<PostVO> createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }

    /**
     * 查询一门课的全部帖子，返回帖子信息以及每个帖子有哪些新回复
     *
     * @param courseId
     * @param pageNo
     * @return
     * @Param number
     */
    @GetMapping("/search/course/{courseId}/{pageNo}/{number}")
    public PageInfo<PostVO> searchPosts(@PathVariable int courseId, @PathVariable int pageNo, @PathVariable int number) {
        return postService.searchPosts(courseId, pageNo, number);
    }

    /**
     * 删除一门课的某个帖子
     *
     * @param postDTO
     * @return
     */
    @PostMapping("/delete")
    public ResultVO deletePost(@RequestBody PostDTO postDTO) {
        return postService.deletePost(postDTO);
    }

    /**
     * 为一个帖子创建一个回复
     *
     * @param replyDTO
     * @return
     */
    @PostMapping("/reply/create")
    public ResultVO<ReplyVO> createReply(@RequestBody ReplyDTO replyDTO) {
        return postService.createReply(replyDTO);
    }

    /**
     * 查询一个帖子的全部回复，
     * 判断是否需要清零新回复数并操作
     *
     * @param postId
     * @param pageNo
     * @return
     */
    @GetMapping("/search/post/{pageNo}/{number}")
    public PageInfo<ReplyVO> searchReplies(@RequestParam int postId, @PathVariable int pageNo, @PathVariable int number, @RequestParam(required = false, defaultValue = "") Integer userId) {
        return postService.searchReplies(postId, pageNo, number, userId);
    }

    /**
     * 创建一个回帖的回复
     *
     * @param replyDTO
     * @return
     */
    @PostMapping("/reply_reply/create")
    public ResultVO<ReplyVO> createReply2Reply(@RequestBody ReplyDTO replyDTO) {
        return postService.createReply2Reply(replyDTO);
    }

    /**
     * 查询一个回复的回复
     * 传入userId
     * 判断是否需要清零新回复数并操作
     *
     * @param repliedId
     * @param pageNo
     * @return
     */
    @GetMapping("/search/reply/{pageNo}/{number}")
    public PageInfo<ReplyVO> searchReply2Reply(@RequestParam int repliedId, @PathVariable int pageNo, @PathVariable int number, @RequestParam(required = false, defaultValue = "") Integer userId) {
        return postService.searchReply2Reply(repliedId, pageNo, number, userId);
    }

    /**
     * 查询一个用户的全部帖子，返回帖子信息以及每个帖子有哪些新回复
     * 不安全
     *
     * @param userId
     * @return
     */
    @GetMapping("/search/userPost/{pageNo}/{number}")
    public PageInfo<PostVO> searchUserPosts(@RequestParam int userId, @PathVariable int pageNo, @PathVariable int number) {
        return postService.searchUserPosts(userId, pageNo, number);
    }

    /**
     * 查询一个用户的全部回复，返回回复信息以及每个回复有哪些新回复
     * 不安全
     *
     * @param userId
     * @return
     */
    @GetMapping("/search/userReply/{pageNo}/{number}")
    public PageInfo<ReplyVO> searchUserReplies(@RequestParam int userId, @PathVariable int pageNo, @PathVariable int number) {
        return postService.searchUserReplies(userId, pageNo, number);
    }

    /**
     * 根据传入的回复id查询回复
     * 更新回复查看状态
     *
     * @param replyIdList
     * @param pageNo
     * @param number
     * @return
     */
    @PostMapping("/search/newReply/{pageNo}/{number}")
    public PageInfo<ReplyVO> searchNewReplies(@RequestParam List<Integer> replyIdList, @PathVariable int pageNo, @PathVariable int number) {
        return postService.searchNewReplies(replyIdList, pageNo, number);
    }
}
