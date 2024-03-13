package icu.leshine.leoj.judge.codesandbox.entity;

import icu.leshine.leoj.model.dto.questionsubmit.JudgeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 代码执行响应封装类
 *
 * @author 乐小鑫
 * @version 1.0
 * @Date 2024-03-13-20:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {

    private List<String> outputList;

    private String message;

    private Integer status;

    private JudgeInfo judgeInfo;

}
