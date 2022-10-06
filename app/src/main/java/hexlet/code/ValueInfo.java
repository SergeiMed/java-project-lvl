package hexlet.code;

public final class ValueInfo<T> {

    private T firstValue;
    private T secondValue;
    private final String status;

    public ValueInfo(T oldValue, T newValue, String statusValue) {
        this.firstValue = oldValue;
        this.secondValue = newValue;
        this.status = statusValue;
    }

    public T getFirstValue() {
        return firstValue;
    }

    public T getSecondValue() {
        return secondValue;
    }

    public String getStatus() {
        return status;
    }

    public void setSecondValue(T newValue) {
        this.secondValue = newValue;
    }

    public void setFirstValue(T oldValue) {
        this.firstValue = oldValue;
    }
}

