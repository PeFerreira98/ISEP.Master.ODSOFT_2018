package pt.isep.cms.turmas.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface TurmaUpdatedEventHandler extends EventHandler {
    void onTurmaUpdated(TurmaUpdatedEvent event);
}
