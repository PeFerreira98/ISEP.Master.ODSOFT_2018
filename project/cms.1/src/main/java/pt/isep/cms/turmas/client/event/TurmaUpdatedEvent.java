package pt.isep.cms.turmas.client.event;

import com.google.gwt.event.shared.GwtEvent;
import pt.isep.cms.turmas.shared.Turma;

public class TurmaUpdatedEvent extends GwtEvent<TurmaUpdatedEventHandler> {
    public static Type<TurmaUpdatedEventHandler> TYPE = new Type<TurmaUpdatedEventHandler>();
    private final Turma updatedTurma;

    public TurmaUpdatedEvent(Turma updatedTurma) {
        this.updatedTurma = updatedTurma;
    }

    public Turma getUpdatedTurma() {
        return updatedTurma;
    }

    @Override
    public Type<TurmaUpdatedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(TurmaUpdatedEventHandler handler) {
        handler.onTurmaUpdated(this);
    }
}
