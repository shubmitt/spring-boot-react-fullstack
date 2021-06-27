import fetch from 'unfetch';


export const getAllStudents = () => 
    fetch('api/student/getAll').then(checkstatus);


const checkstatus = (response) => {
   if(response.ok){
       return response;
   }
   else{
       let error = new Error(response.statusText);
       error.response = response;
       response.json().then(e => {
           error.error = e;
       });
      return Promise.reject(error);
   }
}

export const addStudent = (student) => {
    return fetch('api/student/',{
        headers:{'Content-Type':'application/json'},
         method:'POST',
         body:JSON.stringify(student)
    }).then(checkstatus);
}