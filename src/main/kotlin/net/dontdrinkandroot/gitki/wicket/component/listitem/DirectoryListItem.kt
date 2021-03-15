package net.dontdrinkandroot.gitki.wicket.component.listitem

import net.dontdrinkandroot.gitki.model.DirectoryPath
import net.dontdrinkandroot.gitki.wicket.component.DirectoryActionsDropdownButton
import net.dontdrinkandroot.gitki.wicket.css.GitkiCssClass
import net.dontdrinkandroot.gitki.wicket.model.AbstractPathBasicFileAttributesModel
import net.dontdrinkandroot.gitki.wicket.model.AbstractPathNameModel
import net.dontdrinkandroot.gitki.wicket.model.TemporalAccessorStringModel
import net.dontdrinkandroot.gitki.wicket.page.directory.DirectoryPage
import net.dontdrinkandroot.gitki.wicket.util.PageParameterUtils
import net.dontdrinkandroot.wicket.behavior.addCssClass
import net.dontdrinkandroot.wicket.bootstrap.css.ButtonSize
import net.dontdrinkandroot.wicket.bootstrap.css.FontAwesome5IconClass
import net.dontdrinkandroot.wicket.kmodel.kModel
import net.dontdrinkandroot.wicket.kmodel.model
import net.dontdrinkandroot.wicket.markup.html.basic.addLabel
import net.dontdrinkandroot.wicket.markup.html.link.addPageLink
import net.dontdrinkandroot.wicket.model.nio.file.attribute.BasicFileAttributesLastModifiedTimeModel
import net.dontdrinkandroot.wicket.model.nio.file.attribute.FileTimeInstantModel
import org.apache.wicket.markup.html.panel.GenericPanel
import org.apache.wicket.model.IModel
import org.apache.wicket.model.Model
import java.nio.file.attribute.BasicFileAttributes

class DirectoryListItem(id: String, model: IModel<DirectoryPath>) : GenericPanel<DirectoryPath>(id, model) {

    var attributesModel: IModel<BasicFileAttributes> = AbstractPathBasicFileAttributesModel(model)

    override fun onInitialize() {
        super.onInitialize()

        this.add(
            DirectoryActionsDropdownButton(
                "actions",
                this.model,
                buttonStyleModel = Model(null),
                buttonSizeModel = model(ButtonSize.SMALL),
                prependIconModel = model(FontAwesome5IconClass.ELLIPSIS_V.createIcon(fixedWidth = true))
            )
        )

        addPageLink(
            "link",
            pageClass = DirectoryPage::class.java,
            pageParameters = PageParameterUtils.from(this.modelObject),
            label = AbstractPathNameModel(this.kModel)
        )

        addLabel(
            "lastmodified",
            TemporalAccessorStringModel(
                FileTimeInstantModel(
                    BasicFileAttributesLastModifiedTimeModel(
                        attributesModel
                    )
                )
            )
        )

        addCssClass(GitkiCssClass.DIRECTORY)
    }
}