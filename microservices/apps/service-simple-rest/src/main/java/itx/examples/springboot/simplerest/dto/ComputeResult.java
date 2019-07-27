package itx.examples.springboot.simplerest.dto;

public class ComputeResult {

    private final double result;
    private final String unit;
    private final float durationMs;
    private final int iterations;

    public ComputeResult(double result, String unit, float durationMs, int iterations) {
        this.result = result;
        this.unit = unit;
        this.durationMs = durationMs;
        this.iterations = iterations;
    }

    public double getResult() {
        return result;
    }

    public String getUnit() {
        return unit;
    }

    public float getDurationMs() {
        return durationMs;
    }

    public int getIterations() {
        return iterations;
    }

}
