package icu.leshine.leoj.judge;

import icu.leshine.leoj.model.vo.QuestionSubmitVO;

/**
 * 判题服务
 *
 * @author 乐小鑫
 * @version 1.0
 * @Date 2024-03-14-11:48
 */
public interface JudgeService {

    /**
     * 判题
     *
     * @param questionSubmitId
     * @return
     */
    QuestionSubmitVO doJudge(long questionSubmitId);
}
