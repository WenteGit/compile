package cn.cqut.compiler.IntermediateCode;


import cn.cqut.auto.JFlex.back.Token;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

/**
 * 将结果返回到可视化界面中
 */
public class IntermediateCodeGenerator {
    ArrayList<Token> tokens;

    public IntermediateCodeGenerator(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public void myParse(TextArea codeRightAbove, TextArea codeRightBelow) {
        Parse ic = new Parse(tokens);
        ic.start();
        ArrayList<String[]> intermediateCode = ic.getCode();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < intermediateCode.size(); i++) {
            s.append(i).append("\t( ").
            append(intermediateCode.get(i)[0]).append(" , ").
            append(intermediateCode.get(i)[1]).append(" , ").
            append(intermediateCode.get(i)[2]).append(" , ").
            append(intermediateCode.get(i)[3]).append(" )\n");
        }
        codeRightAbove.setText(s.toString());
        codeRightBelow.setText(ic.getErrorInfo());
    }
}
