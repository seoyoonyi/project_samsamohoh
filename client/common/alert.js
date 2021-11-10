import Swal from "sweetalert2";
export const timer = 2400; //외부로 반출되는 변수
const alertInfo = (_title, _message, _type) => {
  Swal.fire({
    icon: _type,
    title: _title,
    text: _message,
    timer,
    timerProgressBar: true,
  });
};

export default alertInfo;
