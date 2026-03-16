import { Button, Card, TextField } from '@mui/material';
import * as React from 'react';
import CardHeader from '@mui/material/CardHeader';
import { useNavigate } from "react-router-dom";
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';
import axios from 'axios';


function Login() {
    const navigate = useNavigate();
    const [customerID, setCustomerId] = React.useState();
    const [password, setPassword] = React.useState();
    const [disableButton, setDisableButton] = React.useState(true);
    const [snackOpen, setSnackOpen] = React.useState(false);
    const [state, setState] = React.useState({
        vertical: 'top',
        horizontal: 'center',
      });
      const { vertical, horizontal } = state;
    const onCustomerValueChnage = async (val) => {
        setCustomerId(val.target.value);
        if(val.target.value!=null&&password!=null){
            setDisableButton(false)
        }
    }
    const onPasswordValueChnage = async (value) => {
        setPassword(value.target.value);
        if(value.target.value!=null&&customerID!=null){
            setDisableButton(false)
        }
    } 

    const onLogIn = async () => {
        if (customerID != null && password != null) {
            let payload= {
                "username": customerID,
                "password": password
            }
            let uri=`http://localhost:8081/api/customer/login`;
            axios.post(uri,payload).then((val)=>{
                if(val!=null&&val.data!=null&&val.data.success){
                    console.log(val);
                    navigate("/admin");
                }else{
                    setSnackOpen(true)
                }
            }).catch((e)=>{
                setSnackOpen(true)
            });
        }else{
            setSnackOpen(true)
        }
    }

    const handleClose = () => {
        setSnackOpen(false);
    };
    return (
        <div style={{ minHeight: '90vh', marginLeft: '3%', marginRight: '3%', marginTop: '4px', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
            <Card variant='outlined' style={{ marginBottom: '8px', width: '50%', height: '50%' }}>
                <CardHeader style={{ textAlign: 'center' }}
                    title="Welcome Back"
                    subheader="Login into your account"
                />
                <TextField label='Enter UserId'
                    value={customerID}
                    onChangeCapture={onCustomerValueChnage}
                    onChange={onCustomerValueChnage}
                    variant='outlined'
                    size='small'
                    style={{ display: 'flex', borderColor: '#000a00', borderRadius: 16, marginTop: '20px', marginLeft: '50px', marginRight: '50px', marginBottom: '20px' }}
                    color='secondary'
                />
                <TextField label='Enter Password'
                    value={password}
                    onChangeCapture={onPasswordValueChnage}
                    onChange={onPasswordValueChnage}
                    variant='outlined'
                    size='small'
                    type='password'
                    style={{ display: 'flex', borderColor: '#000a00', borderRadius: 16, marginTop: '20px', marginLeft: '50px', marginRight: '50px', marginBottom: '20px' }}
                    color='secondary'
                />
                <div style={{ display: 'flex', marginTop: '40px' }}>
                    <Button disabled={disableButton} disableElevation
                        style={{width:'100%',fontSize:'18px',
                            color: disableButton ? '#b3b3b3' : '#000a00', borderRadius: 8, backgroundColor: disableButton ? '#f2f2f2' : '#4bcd3e', textTransform: 'initial', fontWeight: 'bold',
                            marginLeft: '50px', marginRight: '50px', marginBottom: '20px', display: 'flex'
                        }}
                        variant='contained' onClick={onLogIn} sx={{ height: '100%' }}>
                        Login
                    </Button>
                </div>
            </Card>
            <Snackbar anchorOrigin={{ vertical, horizontal }} open={snackOpen} autoHideDuration={6000} onClose={handleClose}>
                <Alert
                    onClose={handleClose}
                    severity="error"
                    variant="filled"
                    sx={{ width: '100%' }}
                >
                    Invalid UserId and Password!
                </Alert>
            </Snackbar>
        </div>
    );
}

export default Login;