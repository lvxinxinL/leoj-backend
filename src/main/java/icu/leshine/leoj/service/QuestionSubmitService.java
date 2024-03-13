package icu.leshine.leoj.service;

import icu.leshine.leoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
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
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);
}
