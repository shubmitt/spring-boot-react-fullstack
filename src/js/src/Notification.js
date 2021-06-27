import {notification} from 'antd';

const openNotificationWithIcon = (type,message,description) => {
    notification[type]({
      message,
      description
        
    });
  };

  export const successNotification = (message,description) => {
      return openNotificationWithIcon('success',message,description)
  };

  export const errorNotification = (message,description) => {
    return openNotificationWithIcon('error',message,description)
};

export const warnNotification = (message,description) => {
    return openNotificationWithIcon('warning',message,description)
};

export const infoNotification = (message,description) => {
    return openNotificationWithIcon('info',message,description)
};