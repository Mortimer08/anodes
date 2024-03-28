package common.view;

public interface Field {
    public default String getTitle() {
        return "";
    }

    public default String getName() {
        return "";
    }

    public default String getHint() {
        return "";
    }
}
