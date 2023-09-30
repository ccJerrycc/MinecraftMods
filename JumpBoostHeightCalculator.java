/**
 * @author ccJerrycc
 */
public class JumpBoostHeightCalculator {
    /**
     * 以往前跳為基準（因為原地跳除了不明原因的吃 tick、甚至不是 Reproduceable 的 ......
     * 不曉得為什麼有誤差
     * @param args 倍率，-1 表示 無此效果
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.printf("[Error] Got %d, Expected \"1\" Amplifier", args.length);
            return;
        }
        short amplifier;
        try {
            amplifier = Short.parseShort(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("[Error] Wrong Format, Expected \"short\"");
            return;
        }
        if (amplifier < -1 || amplifier > 255) {
            System.out.printf("[Error] Amplifier got %d, Expected between \"-1\" and \"255\"", amplifier);
            return;
        }
        amplifier = (byte) amplifier;

        double speedUp = 0.42 + 0.1 * ((double) amplifier + 1);
        double gravityAccel = -0.08;
        int tick = 0;
        double height = 0;

        while (speedUp > 0.003) {
            height += speedUp;
            speedUp = (speedUp + gravityAccel) * 0.98;
            ++tick;
        }
        System.out.printf("Jump Boost %d can Reach %sm in %dt", amplifier + 1, height, tick);
    }
}