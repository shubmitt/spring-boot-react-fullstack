import React from 'react';
import {Button, Avatar} from 'antd';
import './Footer.css';

const Footer = (props) => {
    return(
        <div className="footer">
            {props.numberOfStudents !== undefined ? <Avatar style={{marginRight:'5px',backgroundColor:'#f56a50'}} size='large'>{props.numberOfStudents}</Avatar>:null}
            <Button type="primary" onClick={props.addStudent}>Add new student +</Button>
        </div>
    );
}

export default Footer;