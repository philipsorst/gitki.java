package net.dontdrinkandroot.gitki.wicket.component.item;

import net.dontdrinkandroot.gitki.model.FilePath;
import net.dontdrinkandroot.gitki.model.Role;
import net.dontdrinkandroot.gitki.wicket.component.modal.RemoveFileModal;
import net.dontdrinkandroot.wicket.bootstrap.component.item.AjaxLinkItem;
import net.dontdrinkandroot.wicket.bootstrap.event.CreateAndOpenModalRequest;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
@AuthorizeAction(action = Action.RENDER, roles = Role.Constants.COMMITTER)
public class RemoveFileModalItem extends AjaxLinkItem
{
    private final IModel<FilePath> pathModel;

    public RemoveFileModalItem(String id, IModel<FilePath> pathModel)
    {
        super(id, Model.of("Remove File"));
        this.pathModel = pathModel;
    }

    @Override
    protected void onClick(AjaxRequestTarget target)
    {
        this.send(
                this.getPage(),
                Broadcast.DEPTH,
                new CreateAndOpenModalRequest<>(target, RemoveFileModal.class, this.pathModel)
        );
    }
}
