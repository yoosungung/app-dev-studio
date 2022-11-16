import React from 'react';
import { observer } from 'mobx-react';
import { Link } from 'react-router-dom';
import { Button, Classes, InputGroup, Label, Tooltip, Intent, Divider } from '@blueprintjs/core';

import UserService from '../service/UserService';

export default observer(function UserLogin() {
    const [showPassword, setShowPassword] = React.useState(false);
    const [loginInfo, setLoginInfo] = React.useState({ loginNm: "", loginPassword: "" });
    const userService = new UserService();

    React.useEffect(() => {
    }, []);

    const login = () => {
        userService.login(loginInfo.loginNm, loginInfo.loginPassword);
    };

    return (
        <div className="center-box" style={{ height: "75vh" }}>
            <div style={{ width: "300px" }}>
                <Label>
                    로그인 아이디
                    <InputGroup
                        leftIcon="user"
                        placeholder="Username"
                        large={true}
                        autoFocus
                        onChange={(event) => {
                            setLoginInfo({ ...loginInfo, loginNm: event.target.value });
                        }}
                        onKeyUp={(event) => {
                            if (event.keyCode === 13) {
                                login();
                            }
                        }}
                    />
                </Label>

                <Label>
                    로그인 암호
                    <InputGroup
                        leftIcon="key"
                        placeholder="Password"
                        type={showPassword ? "text" : "password"}
                        large={true}
                        rightElement={
                            <Tooltip content={`${showPassword ? "암호 숨김" : "암호 표시"}`}>
                                <Button
                                    icon={showPassword ? "unlock" : "lock"}
                                    intent={Intent.WARNING}
                                    minimal={true}
                                    style={{ marginTop: "0px" }}
                                    onClick={() => {
                                        setShowPassword(!showPassword);
                                    }}
                                />
                            </Tooltip>
                        }
                        onChange={(event) => {
                            setLoginInfo({ ...loginInfo, loginPassword: event.target.value });
                        }}
                        onKeyUp={(event) => {
                            if (event.keyCode === 13) {
                                login();
                            }
                        }}
                    />
                </Label>

                <div style={{ textAlign: "center" }}>
                    <Button className={Classes.BUTTON} icon="log-in" intent="primary" large={true}
                        style={{ width: "100%" }}
                        onClick={() => {
                            login();
                        }}
                    >로그인</Button>
                    <Divider />
                    <Link to="/">
                        <Button className={Classes.MINIMAL} icon="home">홈으로</Button>
                    </Link>
                </div>
            </div>
        </div>
    );
});
