package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        MethodSignature methodSignature;
        StringBuilder sb = new StringBuilder(signatureString);
        int start = sb.indexOf("(");
        int end = sb.length();

        String[] arguments = sb.substring(start + 1, end - 1).split(", ");
        List<MethodSignature.Argument> argumentsList = new ArrayList<>();
        if (arguments.length > 1) {
            for (String s : arguments) {
                String type = s.substring(0, s.indexOf(" "));
                String name = s.substring(type.length() + 1);
                MethodSignature.Argument argument = new MethodSignature.Argument(type, name);
                argumentsList.add(argument);
            }
        }
        sb.delete(start, end);
        String[] signatures = sb.toString().split(" ");

        if (signatures.length == 2) {
            methodSignature = new MethodSignature(signatures[1], argumentsList);
            methodSignature.setReturnType(signatures[0]);
        } else {
            methodSignature = new MethodSignature(signatures[2], argumentsList);
            methodSignature.setReturnType(signatures[1]);
            methodSignature.setAccessModifier(signatures[0]);
        }
        return methodSignature;
    }

}
