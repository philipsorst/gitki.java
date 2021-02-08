package net.dontdrinkandroot.gitki.wicket.component

import net.dontdrinkandroot.gitki.model.DirectoryPath
import net.dontdrinkandroot.gitki.model.FilePath
import net.dontdrinkandroot.gitki.model.GitkiPath
import net.dontdrinkandroot.gitki.wicket.component.listitem.DirectoryListItem
import net.dontdrinkandroot.gitki.wicket.component.listitem.FileListItem
import net.dontdrinkandroot.wicket.bootstrap.component.list.ListGroup
import net.dontdrinkandroot.wicket.bootstrap.component.panel.PlainPanel
import org.apache.wicket.Component
import org.apache.wicket.model.IModel
import org.apache.wicket.model.Model

class DirectoryEntriesPanel(id: String, model: IModel<List<GitkiPath>>) : PlainPanel<List<GitkiPath>>(id, model) {

    override fun createAfterBody(id: String): Component {
        return object : ListGroup<GitkiPath>(id, this.model) {
            override fun createListComponent(id: String, model: IModel<GitkiPath>): Component {
                val path = model.getObject()
                return if (path.directoryPath) {
                    val pathModel: IModel<DirectoryPath> = Model.of(path as DirectoryPath)
                    DirectoryListItem(id, pathModel)
                } else {
                    val pathModel: IModel<FilePath> = Model.of(path as FilePath)
                    FileListItem(id, pathModel)
                }
            }
        }
    }
}