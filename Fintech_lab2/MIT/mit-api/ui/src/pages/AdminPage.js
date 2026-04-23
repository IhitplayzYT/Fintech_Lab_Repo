import CustomerList from "../component/CustomerList";
import { Button, Card, TextField } from '@mui/material';
import * as React from 'react';
import LinearProgress from '@mui/material/LinearProgress';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Slide from '@mui/material/Slide';
import CloseOutlinedIcon from '@mui/icons-material/CloseOutlined';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import Stepper from '@mui/material/Stepper';
import Step from '@mui/material/Step';
import StepLabel from '@mui/material/StepLabel';
import BasicDtl from "../component/BasicDtl";
import AddressDtl from "../component/AddressDtl";
import axios from 'axios';
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';

const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />;
});

function Admin() {
    const [progress, setProgress] = React.useState(true);
    const [disableButton, setDisableButton] = React.useState(false);
    const [customerIdTypeList, setCustomerIdTypeList] = React.useState([
        {
            name: "All",
            value: 'includeAll'
        },
        {
            name: "None",
            value: 'None'
        },
        {
            name: "Platinum",
            value: 'Platinum'
        },
        {
            name: "Gold",
            value: 'Gold'
        },
        {
            name: "Silver",
            value: 'Silver'
        },
    ]);
    const [customerIdType, setCustomerIdType] = React.useState("includeAll");
    const [customerId, setCustomerId] = React.useState();
    const [resultRows, setResultRows] = React.useState([]);
    const [steperIndex, setSteperIndex] = React.useState(0);
    const [stepData, setStepData] = React.useState({});
    const steps = [
        'Basic Details',
        'Additional Details',
        'Create Customer',
    ];
    const [open, setOpen] = React.useState(false);
    const [snackOpen, setSnackOpen] = React.useState(false);
    const [state, setState] = React.useState({
        vertical: 'top',
        horizontal: 'center',
      });
      const { vertical, horizontal } = state;

    const handleClickOpen = () => {
        setSteperIndex(0);
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
        setSnackOpen(false);
    };
    const onCustomerIdOpen = async () => {
    }
    const handleOnValueChnage = async (value) => {
        if(value.target.value!=null){
            setCustomerId(value.target.value);
        }
    }
    const onSearch = async () => {
        setProgress(false);
        if (customerId != null && customerId.length>0) {
            try {
                const hostUrl = "http://localhost:8081";
                let uri = `${hostUrl}/api/customer/getCustomerDetails/${customerId}`;
                const response = await axios.get(uri);
                if (response.data != null) {
                    let row = [];
                    let value = response.data.data;
                    let item = {
                        id: 1,
                        customerId: value.customerid,
                        customerName: value.customerName[0].value,
                        customerStatus: value.customerStatus,
                        customerGender: value.customerGender,
                        customerPreferredLanguage: value.customerPreferredLanguage,
                        customerCountryOrigin: value.customerCountryOrigin,
                        customerType: value.customerType,
                        customerDOB: value.customerDOB,
                        names:value.customerName
                    }
                    row.push(item);
                    setResultRows([...row]);
                    setProgress(true);
                    setDisableButton(false);
                }
            } catch (error) {
                setProgress(true);
                setDisableButton(false);
            }
        } else {
            try {
                const hostUrl = "http://localhost:8081";
                let uri = `${hostUrl}/api/customer/getCustomers?custType=${customerIdType}`;
                const response = await axios.get(uri);
                if (response.data != null) {
                    let row = [];
                    response.data.data.map((value, index) => {
                        let item = {
                            id: index + 1,
                            customerId: value.customerid,
                            customerName: value.customerName[0].value,
                            customerStatus: value.customerStatus,
                            customerGender: value.customerGender,
                            customerPreferredLanguage: value.customerPreferredLanguage,
                            customerCountryOrigin: value.customerCountryOrigin,
                            customerType: value.customerType,
                            customerDOB: value.customerDOB,
                            names:value.customerName
                        }
                        row.push(item);
                        console.log(item);
                    });
                    setResultRows([...row]);
                    setProgress(true);
                    setDisableButton(false);
                }
            } catch (error) {
                setProgress(true);
                setDisableButton(false);
            }
        }
    }
    const onNextClick = async (value) => {
        if(value.index===1){
            let result={...stepData,basic:value};

            setStepData(result);
        }       if(value.index===2){
            let result={...stepData,addtl:value};
            setStepData(result);
        }
        setSteperIndex(value.index);
        console.log(stepData);
    }
    const onBackClick = async (value) => {
        setSteperIndex(value.index)
    }
    const onConfirmClick = async () => {
        setOpen(false);
        setProgress(false);
        console.log(stepData);
        let body={};
        if(stepData?.basic!=null){
            let name=[];
            if(stepData?.basic.firstName!=null&&stepData?.basic.firstName!==undefined){
                let value={
                    type:'FirstName',
                    value:stepData?.basic.firstName
                }
                name.push(value);
            }
            if(stepData?.basic.middleName!=null&&stepData?.basic.middleName!==undefined){
                let value={
                    type:'MiddleName',
                    value:stepData?.basic.middleName
                }
                name.push(value);
            }
            if(stepData?.basic.lastName!=null&&stepData?.basic.lastName!==undefined){
                let value={
                    type:'LastName',
                    value:stepData?.basic.lastName
                }
                name.push(value);
            }
            if(name.length>1){
                body.customerName=name;
            }
            if(stepData?.basic.dob!=null){
                body.customerDOB=stepData?.basic.dob;
            }
            if(stepData?.basic.gender!=null){
                body.customerGender=stepData?.basic.gender;
            }
            if(stepData?.addtl.countryOfOrigin!=null){
                body.customerCountryOrigin=stepData?.addtl.countryOfOrigin;
            }
            if(stepData?.addtl.preferredLanguage!=null){
                body.customerPreferredLanguage=stepData?.addtl.preferredLanguage;
            }
            if(stepData?.addtl.status!=null){
                body.customerStatus=stepData?.addtl.status;
            }
            if(stepData?.addtl.type!=null){
                body.customerType=stepData?.addtl.type;
            }
        }
        try {
            const hostUrl="http://localhost:8081";
            let uri=`http://localhost:8081/api/customer/addCustomerDetails`;
            const response = await axios.post(uri,body);
            console.log(response);
            setProgress(true);
            setSnackOpen(true);
            onSearch();
        } catch (error) {
            setProgress(true);
        }
    }
    return (
        <div style={{ minHeight: '90vh', marginLeft: '3%', marginRight: '3%', marginTop: '4px', display: 'flex',flexWrap:'wrap', alignItems: 'start', justifyContent: 'start' }}>
            <Card variant='outlined' style={{ marginTop: '10px', marginBottom: '0px', width: '100%' }}>
                <div style={{ display: 'flex', justifyContent: 'start', margin: '2%' }}>
                    <FormControl style={{ width: '30%', marginRight: '20px' }}>
                        <InputLabel color='secondary'>CustomerId Type </InputLabel>
                        <Select size='small'
                            value={customerIdType}
                            label="CustomerId Type"
                            variant='outlined'
                            sx={{ width: '100%', mr: 2 }}
                            style={{ borderColor: '#000a00' }}
                            color='secondary'
                            onOpen={onCustomerIdOpen}
                            onChange={(value) => setCustomerIdType(value.target.value)}
                        >
                            {
                                customerIdTypeList.map((value) => (
                                    <MenuItem value={value.value}>{`${value.name}`}</MenuItem>
                                ))
                            }
                        </Select>
                    </FormControl>
                    <TextField label='Search CustomerId'
                        value={customerId}
                        onChangeCapture={handleOnValueChnage}
                        onChange={handleOnValueChnage}
                        variant='outlined'
                        size='small'
                        sx={{ width: '50%', mr: 2 }}
                        style={{ borderColor: '#000a00', borderRadius: 16 }}
                        color='secondary'
                    />

                    <Button disabled={disableButton} disableElevation style={{ color: disableButton ? '#b3b3b3' : '#000a00', borderRadius: 8, backgroundColor: disableButton ? '#f2f2f2' : '#4bcd3e', textTransform: 'initial', fontWeight: 'bold' }}
                        variant='contained' onClick={onSearch} sx={{ height: '100%' }}>
                        Search
                    </Button>
                    <Button disableElevation style={{ marginLeft: '16px', color: '#000a00', borderRadius: 8, backgroundColor: '#4bcd3e', textTransform: 'initial', fontWeight: 'bold' }}
                        variant='contained' onClick={handleClickOpen} sx={{ height: '100%' }}>
                        Add Customer
                    </Button>
                </div>
                {
                    !progress ? <LinearProgress color="success" /> : <div />
                }
            </Card>
            {
                resultRows != null && resultRows.length > 0 ? <CustomerList row={resultRows} onSearch={onSearch}/> : <div />
            }
            <Dialog
                fullWidth={true}
                maxWidth='sm'
                open={open}
                TransitionComponent={Transition}
                keepMounted
                onClose={handleClose}
            >
                <DialogTitle>
                    <ListItem onClick={handleClose}>
                        <ListItemText primary="Register Customer" style={{ fontWeight: 'bold' }} />
                        <CloseOutlinedIcon />
                    </ListItem>
                </DialogTitle>
                <DialogContent>
                    <Stepper activeStep={steperIndex} alternativeLabel>
                        {steps.map((label) => (
                            <Step key={label}>
                                <StepLabel>{label}</StepLabel>
                            </Step>
                        ))}
                    </Stepper>
                    {
                        steperIndex === 0 ? <BasicDtl onNextClick={onNextClick} /> : steperIndex === 1 ? <AddressDtl onNextClick={onNextClick} onBackClick={onBackClick} /> : <div style={{ marginLeft: '50px', marginTop: '30px' }}>
                            All required details are received click on confirm to onboard the customer.
                            <div style={{ display: 'flex' }}>
                                <Button disableElevation style={{marginTop: '30px', alignItems: 'end', borderColor: '#4bcd3e', color: '#4bcd3e', textTransform: 'initial', fontWeight: 'bold' }}
                                    variant='outlined' onClick={() => setSteperIndex(1)} sx={{ height: '100%', width: '13vw' }}>
                                    Back
                                </Button>
                                <Button disableElevation style={{ marginLeft: '50px', marginTop: '30px', alignItems: 'end', color:'#000a00', borderRadius: 8, backgroundColor: '#4bcd3e', textTransform: 'initial', fontWeight: 'bold' }}
                                    variant='contained' onClick={ onConfirmClick} sx={{ height: '100%', width: '13vw' }}>
                                    Confirm
                                </Button>
                            </div>
                        </div>
                    }
                </DialogContent>
            </Dialog>
            <Snackbar anchorOrigin={{ vertical, horizontal }} open={snackOpen} autoHideDuration={6000} onClose={handleClose}>
                <Alert
                    onClose={handleClose}
                    severity="success"
                    variant="filled"
                    sx={{ width: '100%' }}
                >
                    Customer has onboarded successfully!
                </Alert>
            </Snackbar>
        </div>
    );
}

export default Admin;