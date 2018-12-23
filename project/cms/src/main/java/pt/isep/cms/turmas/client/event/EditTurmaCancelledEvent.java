package pt.isep.cms.turmas.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditTurmaCancelledEvent extends GwtEvent<EditTurmaCancelledEventHandler> {
    public static final Type<EditTurmaCancelledEventHandler> TYPE = new Type<EditTurmaCancelledEventHandler>();

    @Override
    public Type<EditTurmaCancelledEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(EditTurmaCancelledEventHandler handler) {
        handler.onEditTurmaCancelled(this);
    }
}
