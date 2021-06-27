import React from 'react';

 const Container = (props) => {
    return (
        <div style={{width:'1400px',marginBottom:'140px',marginLeft:'auto',marginRight:'auto'}}>
            {props.children}
        </div>
    );
}

export default Container;