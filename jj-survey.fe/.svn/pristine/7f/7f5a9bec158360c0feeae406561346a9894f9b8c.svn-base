import { trackPromise } from 'react-promise-tracker';
import { toast } from 'react-toastify';

import CommonUtil from '../../util/CommonUtil';
import StoreUtil from '../../util/StoreUtil';

export default class UserService {
    /**
     * 로그인
     *
     * @param loginNm
     * @param loginPassword
     */
    public login(loginNm: string, loginPassword: string): void {
        if (!loginNm || loginNm === "") {
            toast.error("로그인 아이디를 입력하세요.", {
                position: toast.POSITION.TOP_CENTER,
                autoClose: 3000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
            });
            return;
        }

        if (!loginPassword || loginPassword === "") {
            toast.error("로그인 암호를 입력하세요.", {
                position: toast.POSITION.TOP_CENTER,
                autoClose: 3000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
            });
            return;
        }

        const { userStore } = StoreUtil.useStores();

        const formData = new FormData();
        formData.append("loginNm", loginNm!);
        formData.append("loginPassword", loginPassword!);

        trackPromise(
            CommonUtil.axios()
            .post("/common/user/login.do", formData)
            .then((response) => {
                userStore.readUser((): void => {
                }, (): void => {
                    window.location.reload();
                });
            })
            .catch((error) => {
                CommonUtil.showAxiosError(error);
            })
        );
    }

    public logout() {
        const { notifyStore } = StoreUtil.useStores();

        notifyStore.confirm({
            contents: "로그아웃 하시겠습니까?",
            icon: "log-out",
            width: "500px",
            onConfirm: () => {
                trackPromise(
                    CommonUtil.axios()
                    .post("/common/user/logout.do")
                    .then((response) => {
                        window.location.replace(CommonUtil.contextPath + "/");
                    })
                    .catch((error) => {
                        CommonUtil.showAxiosError(error);
                    })
                );
            }
        });
    }
}
