package pt.isep.cms.turmas.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface TurmaDeletedEventHandler extends EventHandler {
    void onTurmaDeleted(TurmaDeletedEvent event);
}
