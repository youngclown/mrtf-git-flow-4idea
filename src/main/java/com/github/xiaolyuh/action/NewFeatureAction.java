package com.github.xiaolyuh.action;

import com.github.xiaolyuh.GitNewBranchNameValidator;
import com.github.xiaolyuh.i18n.I18n;
import com.github.xiaolyuh.i18n.I18nKey;
import com.github.xiaolyuh.utils.ConfigUtil;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.IconLoader;
import com.intellij.util.ReflectionUtil;
import git4idea.GitUtil;

import java.util.Objects;

/**
 * 新建开发分支
 *
 * @author yuhao.wang3
 */
public class NewFeatureAction extends AbstractNewBranchAction {

    public NewFeatureAction() {
        super("새로운 개발 지점", "새 개발 브랜치를 생성하고 원격웨어 하우스로 푸시",
                IconLoader.getIcon("/icons/feature.svg", Objects.requireNonNull(ReflectionUtil.getGrandCallerClass())));
    }

    @Override
    protected void setEnabledAndText(AnActionEvent event) {
        event.getPresentation().setText(I18n.getContent(I18nKey.NEW_FEATURE_ACTION$TEXT));
    }

    @Override
    public String getPrefix(Project project) {
        return ConfigUtil.getConfig(project).get().getFeaturePrefix();
    }

    @Override
    public String getInputString(Project project) {
        return Messages.showInputDialog(project, I18n.getContent(I18nKey.NEW_FEATURE_ACTION$DIALOG_MESSAGE),
                I18n.getContent(I18nKey.NEW_FEATURE_ACTION$DIALOG_TITLE), null, "",
                GitNewBranchNameValidator.newInstance(GitUtil.getRepositoryManager(project).getRepositories(), getPrefix(project)));
    }

    @Override
    public String getTitle(String branchName) {
        return I18n.getContent(I18nKey.NEW_FEATURE_ACTION$TITLE) + ": " + branchName;
    }

    @Override
    public boolean isDeleteBranch() {
        return false;
    }
}
