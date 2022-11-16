import { toast } from 'react-toastify';

import CommonUtil from '../../util/CommonUtil';

/**
 * 메뉴 로딩 Service
 */
export default class MenuLoadService {
    /**
     * 전체 목록 조회
     *
     * @param menuKnd
     * @param callback
     */
    public readAllList(menuKnd: string, callback: Function) {
        CommonUtil.axios()
        .get(`/common/menu/MenuLoad/${menuKnd}/readAllList`)
        .then((response) => {
            callback(response.data);
        })
        .catch((error) => {
            toast.error(error.response.data.message, {
                position: toast.POSITION.TOP_CENTER,
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
            });
        });
    }
}
