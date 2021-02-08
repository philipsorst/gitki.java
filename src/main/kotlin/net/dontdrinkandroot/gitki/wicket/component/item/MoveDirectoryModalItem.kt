package net.dontdrinkandroot.gitki.wicket.component.item

import net.dontdrinkandroot.gitki.model.DirectoryPath
import net.dontdrinkandroot.gitki.model.Role
import net.dontdrinkandroot.gitki.wicket.component.modal.MoveDirectoryModal
import net.dontdrinkandroot.gitki.wicket.security.Render
import net.dontdrinkandroot.wicket.bootstrap.component.item.AjaxLinkItem
import net.dontdrinkandroot.wicket.bootstrap.css.FontAwesomeIconClass
import net.dontdrinkandroot.wicket.bootstrap.event.CreateAndOpenModalRequest
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.event.Broadcast
import org.apache.wicket.model.IModel
import org.apache.wicket.model.StringResourceModel

@Render(Role.COMMITTER)
class MoveDirectoryModalItem(id: String, private val pathModel: IModel<DirectoryPath>) :
    AjaxLinkItem<Void?>(id, StringResourceModel("gitki.move")) {

    override fun onClick(target: AjaxRequestTarget) {
        send(
            this.page,
            Broadcast.DEPTH,
            CreateAndOpenModalRequest(target, MoveDirectoryModal::class.java, pathModel)
        )
    }

    init {
        setPrependIcon(FontAwesomeIconClass.ARROWS_H.createIcon().setFixedWidth(true))
    }
}