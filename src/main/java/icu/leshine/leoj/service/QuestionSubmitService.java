package icu.leshine.leoj.service;

import icu.leshine.leoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.leshine.leoj.model.entity.User;

/**
* @author 20890
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-03-12 16:10:55
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 提交
     *
     * @param postId
     * @param loginUser
     * @return
     */
    int doQuestionSubmit(long postId, User loginUser);

    /**
     * 题目提交（内部服务）
     *
     * @param userId
     * @param postId
     * @return
     */
    int doQuestionSubmitInner(long userId, long postId);
}
