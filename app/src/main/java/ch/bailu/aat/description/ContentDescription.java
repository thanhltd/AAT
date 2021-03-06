package ch.bailu.aat.description;

import android.content.Context;

import ch.bailu.aat.dispatcher.OnContentUpdatedInterface;


public abstract class ContentDescription
        implements ContentInterface, OnContentUpdatedInterface {

    private final Context context;

    protected static final String NULL_STRING="";
    public ContentDescription(Context c) {
        context = c;
    }

    public Context getContext() {
        return context;
    }


    public abstract String getValue();
    public abstract String getLabel();

    /*
    public String getApLabel(String label) {
        return label + " (" + getContext().getString(R.string.p_autopause) + ")";
    }
*/

    public String getUnit() {
        return NULL_STRING;
    }


    @Override
    public String getValueAsString() {
        return getValue() + " " + getUnit();
    }
}
