package cn.cqut.compiler.IntermediateCode;

import cn.cqut.auto.JFlex.back.*;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

/**
 * @author Wente
 * @date 2023/4/25
 * 语义分析生成器
 **/
public class SemanticAnalysisGenerator {
    ArrayList<Token> tokens;

    public SemanticAnalysisGenerator(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public void myParse(TextArea codeRightAbove, TextArea codeRightBelow) {
        Parse ic = new Parse(tokens);
        ic.start();
        // 常量表
        ConstantTable constantTable = ic.getConstantTable();
        ArrayList<String> constantName = constantTable.constantName;
        ArrayList<Integer> constantType = constantTable.constantType;
        ArrayList<String> constantValue = constantTable.value;

        // 函数表
        FunctionTable functionTable = ic.getFunctionTable();
        // 方法名
        ArrayList<String> functionName = functionTable.functionName;
        // 参数名
        ArrayList<ArrayList<Integer>> parameterInd = functionTable.parameterInd;
        // 返回类型
        ArrayList<Integer> returnType = functionTable.returnType;
        // 参数值
        ArrayList<ArrayList<Integer>> parameterList = functionTable.parameterList;


        // 变量表
        VariableTable variableTable = ic.getVariableTable();
        ArrayList<String> variableName = variableTable.variableName;
        ArrayList<Integer> variableType = variableTable.variableType;
        ArrayList<String> variableValue = variableTable.value;


        StringBuilder str = new StringBuilder();
        str.append("变量表\n");
        str.append("序号 | (变量名,变量类型,变量值)\n");
        for (int i = 0;i<variableName.size();i++){
            String tempType = sym.getMeans(variableType.get(i));
            str.append(i).append("\t(").
                    append(variableName.get(i)).append(" , ").
                    append(tempType).append(" , ").
                    append(variableValue.get(i)).append(")\n");
        }

        str.append("常量表\n");
        str.append("序号 | (常量名,常量类型,常量值)\n");
        for (int i = 0;i<constantName.size();i++){
            String tempType = sym.getMeans(constantType.get(i));
            str.append(i).append("\t(").
                    append(constantName.get(i)).append(" , ").
                    append(tempType).append(" , ").
                    append(constantValue.get(i)).append(")\n");
        }

        str.append("函数表\n");
        str.append("序号 | (方法名,返回类型,参数个数)\n");
        for (int i = 0;i<functionName.size();i++){
            // 临时返回类型
            String tempType = sym.getMeans(variableType.get(i));
            str.append(i).append("\t(").
                    append(functionName.get(i)).append(",").
                    append(tempType).append(",").
                    append(parameterInd.get(i).size()).append(")\n");
        }


        codeRightAbove.setText(str.toString());
        codeRightBelow.setText(ic.getErrorInfo());
    }


}
