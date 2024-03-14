package icu.leshine.leoj.judge.strategy;

import icu.leshine.leoj.judge.strategy.impl.DefaultJudgeStrategy;
import icu.leshine.leoj.judge.strategy.impl.JavaLangJudgeStrategy;
import icu.leshine.leoj.model.dto.questionsubmit.JudgeInfo;
import icu.leshine.leoj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 管理判题策略的选择
 *
 * @author 乐小鑫
 * @version 1.0
 * @Date 2024-03-14-15:57
 */
@Service
public class JudgeManager {

    /**
     * 根据语言选择判题策略
     *
     * @param judgeContext
     * @return
     */
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        JudgeStrategyService judgeStrategyService = new DefaultJudgeStrategy();;
        if (questionSubmit.getLanguage().equals("java")) {
            judgeStrategyService = new JavaLangJudgeStrategy();
        }
        return judgeStrategyService.doJudge(judgeContext);
    }
}
