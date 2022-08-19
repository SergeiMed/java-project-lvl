package hexlet.code;

public final class ValueInfo<T> {

    private final T firstValue;
    private final T secondValue;
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
}

