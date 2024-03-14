package icu.leshine.leoj.judge.strategy;

import icu.leshine.leoj.model.dto.questionsubmit.JudgeInfo;
import org.springframework.stereotype.Service;

/**
 * 判题策略
 *
 * @author 乐小鑫
 * @version 1.0
 * @Date 2024-03-14-14:56
 */
@Service
public interface JudgeStrategyService {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
