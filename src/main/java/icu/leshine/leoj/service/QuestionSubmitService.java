package icu.leshine.leoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.leshine.leoj.model.dto.question.QuestionQueryRequest;
import icu.leshine.leoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import icu.leshine.leoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import icu.leshine.leoj.model.entity.Question;
import icu.leshine.leoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.leshine.leoj.model.entity.User;
import icu.leshine.leoj.model.vo.QuestionSubmitVO;
import icu.leshine.leoj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
