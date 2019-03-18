package service;

import org.openqa.selenium.WebElement;
import page.AccountPage;

import java.util.List;

public class LikeService extends BaseService {

    private AccountPage accountPage;
    private DialogService dialogService;

    public boolean isPostLiked() {
        String like = "Like";
        accountPage = new AccountPage();
        if (accountPage.getLikeHeart().getAttribute("aria-label").equalsIgnoreCase(like)) {
            return false;
        }
        return true;
    }

    public void clickLikeButton() {
        accountPage = new AccountPage();
        accountPage.getLikeButton().click();
    }

    public void likePost() {
        if(!isPostLiked()) {
            clickLikeButton();
        }
    }

    public void unlikePost() {
        if(isPostLiked()) {
            clickLikeButton();
        }
    }

    public void likeAllVisiblePosts() {
        accountPage = new AccountPage();
        List<WebElement> userPosts = accountPage.getUserPosts();
        for (WebElement post : userPosts) {
            post.click();
            likePost();
            accountPage.clickCloseButton();
        }
    }

    public void unlikeAllVisiblePosts() {
        accountPage = new AccountPage();
        List<WebElement> userPosts = accountPage.getUserPosts();
        for (WebElement post : userPosts) {
            post.click();
            unlikePost();
            accountPage.clickCloseButton();
        }
    }

    public void likeAllPosts() {
        accountPage = new AccountPage();
        dialogService = new DialogService();
        int postsQTY = accountPage.getNumberOfPostsText() - 1;
        accountPage.clickUserFirstPost();
        int i = 0;
        while (accountPage.isNextPostButtonExist()) {
            likePost();
            //dialogService.isBlockerDialogClickable();
            accountPage.clickNextPostButton();
            i++;
            if (i == postsQTY) {
                break;
            }
        }
        accountPage.clickCloseButton();
    }

    public void unlikeAllPosts() {
        accountPage = new AccountPage();
        dialogService = new DialogService();
        int postsQTY = accountPage.getNumberOfPostsText() - 1;
        accountPage.clickUserFirstPost();
        int i = 0;
        while (accountPage.isNextPostButtonExist()) {
            unlikePost();
            if (dialogService.isBlockerDialogClickable()) {
                accountPage.waitForLikeHeartToBeUnliked();
            }
            accountPage.clickNextPostButton();
            i++;
            if (i == postsQTY) {
                break;
            }
        }
        accountPage.clickCloseButton();
    }

}
