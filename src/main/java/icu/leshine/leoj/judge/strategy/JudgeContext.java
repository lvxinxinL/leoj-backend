package icu.leshine.leoj.judge.strategy;

import icu.leshine.leoj.judge.codesandbox.entity.ExecuteCodeResponse;
import icu.leshine.leoj.model.dto.question.JudgeCase;
import icu.leshine.leoj.model.dto.questionsubmit.JudgeInfo;
import icu.leshine.leoj.model.entity.Question;
import icu.leshine.leoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 上下文对象：判题策略所需信息
 *
 * @author 乐小鑫
 * @version 1.0
 * @Date 2024-03-14-14:57
 */
@Data
public class JudgeContext {

    private Question question;

    private QuestionSubmit questionSubmit;

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;
}
