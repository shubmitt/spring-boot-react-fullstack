import logo from './logo.svg';
import './App.css';
import {getAllStudents,addStudent} from './client';
import {useState,useEffect} from 'react';
import Container from './Container';
import {Table,Avatar,Spin,Modal,Input,Button,Notification, Empty} from 'antd';
import Footer from './Footer';
import {useFormik} from 'formik';
import {errorNotification} from './Notification';


function App() {
  const [students,setStudent] = useState([]);
  const [loading,setLoading] = useState(false);
  const [modelOpen,setModelOpen] =useState(false);
  const inputBottomMargin={marginBottom:"10px"};
  const fetchStudents = () => {
    getAllStudents()
    .then(resp => resp.json()
    
    .then(students => {setStudent(students);setLoading(false);}))
    .catch((error) => {
      errorNotification(error.error.message,error.error.error);
      setLoading(false);
    });
  }
 useEffect(() =>  {setLoading(true);fetchStudents()},[]);
  
  const columns = [
    {
      key:'avatar',
      title:'',
     render:(text,student) => (
       <Avatar size='large'>
         {`${student.firstName.charAt(0).toUpperCase()}${student.lastName.charAt(0).toUpperCase()}`}
       </Avatar>
     )
      
    },
     {
        key:'studentId',
        dataIndex:'studentId',
        title:'StudentID'
      },
      {
        key:'firstName',
        dataIndex:'firstName',
        title:'First Name'
      },
      {
        key:'lastName',
        dataIndex:'lastName',
        title:'Last Name'
      },
      {
        key:'email',
        dataIndex:'email',
        title:'Email'
      },
      {
        key:'gender',
        dataIndex:'gender',
        title:'Gender'
      }

  ];
    
  //const antIcon = <Icon type='loading' style={{ fontSize: 24 }} />;
      const openModal = () => setModelOpen(true);
      const closeModal = () => setModelOpen(false);
      const formik = useFormik(
        {
          initialValues:{
            studentId:null,
            firstName:'',
            lastName:'',
            email:'',
            gender:''
          },
          validate : (values, props /* only available when using withFormik */) => {
            const errors = {};
            if (!values.firstName) {
              errors.firstName = 'Required';
              console.log("Required first name");
            } else if (values.firstName.length > 15) {
              errors.firstName = 'Must be 15 characters or less';
            }
          
            if (!values.lastName) {
              errors.lastName = 'Required';
            } else if (values.lastName.length > 20) {
              errors.lastName = 'Must be 20 characters or less';
            }
            if (!values.email) {
              errors.email = 'Required';
            } else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(values.email)) {
              errors.email = 'Invalid email address';
            }

            if (!values.gender) {
              errors.gender = 'Required';
            } else if (!['MALE','FEMALE'].includes(values.gender)) {
              errors.gender = 'Gender can only be male or female';
            }
          
            //...
          
            return errors;
          },
          onSubmit: (values,{setSubmitting}) => {
             setTimeout(() => {
               addStudent(values).then(() => {
                closeModal();
                fetchStudents();
               })
               .catch(error => {
                 errorNotification(error.error.message,error.error.error);
                closeModal();
               fetchStudents();
               setLoading(false);
              });
              setSubmitting(false);
            },400)
          }
        }
      )
    const commonElements = () => {
      return(
      <div>
      <Modal title="Add Student" 
      onCancel={closeModal}
      onOk={closeModal}
      visible={modelOpen}>
        <title>Add new student</title>
       <form onSubmit={formik.handleSubmit}>
       <label htmlFor="firstName">First Name</label>
         <Input  id="firstName"
           name="firstName"
           value={formik.values.firstName}
           onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          placeholder="First Name"
          style={inputBottomMargin}
         />
          {formik.errors.firstName && formik.touched.firstName ?<div>{formik.errors.firstName}</div>:''}
 
 <label htmlFor="lastName">Last Name</label>
         <Input  id="lastName"
           name="lastName"
           value={formik.values.lastName}
           onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          placeholder="Last Name"
          style={inputBottomMargin}
         />
         {formik.errors.lastName && formik.touched.lastName ? <div>{formik.errors.lastName}</div>:''}
 
 <label htmlFor="firstName">Gender</label>
         <Input id="gender"
           name="gender"
           value={formik.values.gender}
           onChange={formik.handleChange}
          onBlur={formik.handleBlur}
          placeholder="Gender MALE/FEMALE"
          style={inputBottomMargin}
         />
    {formik.errors.gender  && formik.touched.gender ?<div>{formik.errors.gender}</div>:''}
 
      <label htmlFor="email">Email Address</label>
        <Input
          id="email"
          name="email"
          type="email"
          onChange={formik.handleChange}
          value={formik.values.email}
          placeholder="Email"
          style={inputBottomMargin}
        />
        {formik.errors.email  && formik.touched.email ?<div>{formik.errors.email}</div>:''}
        <button type="submit" onClick={() => formik.handleSubmit} disabled={formik.isSubmitting || !(formik.touched && formik.isValid)}>Submit</button>
       </form>
   </Modal>
   <Footer numberOfStudents={students.length} addStudent={openModal}/>
   </div>
    )}
  return (
  loading ? <Container><Spin size='large' spinning={loading}/></Container>
 : students && students.length ? (<Container>
    
   <Table rowKey='studentId'
  dataSource={students}
  columns={columns}
  style={{marginBottom:'10px'}}
  />
 {commonElements()}
  </Container>):
   <Container>
     <Empty description={<h1>No Data found</h1>}></Empty>
     {commonElements()}
   </Container>
  );
}

export default App;
