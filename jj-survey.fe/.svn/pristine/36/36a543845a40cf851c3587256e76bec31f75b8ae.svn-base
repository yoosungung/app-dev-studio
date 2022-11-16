import React from 'react';
import { NavLink } from 'react-router-dom';
import { Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, UncontrolledDropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';

import CommonUtil from '../common/util/CommonUtil';

export default function Main() {
    const [isOpen, setIsOpen] = React.useState(false);

    const toggle = () => setIsOpen(!isOpen);

    return (
        <>
        <Navbar color="light" light expand="md">
            <NavbarBrand href={`${CommonUtil.contextPath}/`}>설문관리 시스템</NavbarBrand>
            <NavbarToggler onClick={toggle} />
            <Collapse isOpen={isOpen} navbar>
                <Nav className="mr-auto" navbar>
                    <NavItem>
                        <NavLink to="/components/" className="nav-link">Component</NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink to="/admin/" className="nav-link">Admin</NavLink>
                    </NavItem>
                </Nav>
                <Nav>
                    <UncontrolledDropdown nav inNavbar>
                        <DropdownToggle nav caret>
                            Options
                        </DropdownToggle>
                        <DropdownMenu right>
                            <DropdownItem>Option 1</DropdownItem>
                            <DropdownItem>Option 2</DropdownItem>
                            <DropdownItem divider />
                            <DropdownItem>Reset</DropdownItem>
                        </DropdownMenu>
                    </UncontrolledDropdown>
                    <li>
                        Simple Text
                    </li>
                </Nav>
            </Collapse>
        </Navbar>
        </>
    );
}
