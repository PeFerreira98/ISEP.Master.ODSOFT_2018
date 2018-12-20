package pt.isep.cms.students.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditStudentEvent extends GwtEvent<EditStudentEventHandler> {
    public static final Type<EditStudentEventHandler> TYPE = new Type<EditStudentEventHandler>();
    private final int id;

    public EditStudentEvent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public Type<EditStudentEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(EditStudentEventHandler handler) {
        handler.onEditStudent(this);
    }
}
