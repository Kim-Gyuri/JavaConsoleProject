package console.ghostLeg.util;

// 입력을 사용 가능한 타입[배열]으로 변환해준다.
public class SplitGenerator {
    public static String[] splitWithSign(String expression, String sign) {
        return expression.split(sign);
    }
}
