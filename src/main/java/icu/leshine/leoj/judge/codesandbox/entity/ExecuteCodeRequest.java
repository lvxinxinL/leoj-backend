package icu.leshine.leoj.judge.codesandbox.entity;

import icu.leshine.leoj.model.dto.questionsubmit.JudgeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 代码执行请求封装类
 *
 * @author 乐小鑫
 * @version 1.0
 * @Date 2024-03-13-20:35
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeRequest {

    private List<String> inputList;

    private String code;

    private String language;
}
