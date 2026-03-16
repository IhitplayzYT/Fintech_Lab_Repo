import { Button, Card, TextField } from '@mui/material';
import * as React from 'react';
import LinearProgress from '@mui/material/LinearProgress';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import Slide from '@mui/material/Slide';
import axios from 'axios';
import CustomerHistoryList from "../component/customerHistoryList";

const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />;
});

function CustomerHistory() {
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

    const [state, setState] = React.useState({
        vertical: 'top',
        horizontal: 'center',
      });
      const { vertical, horizontal } = state;

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
                let uri = `${hostUrl}/api/customer/getAllHistory?custType=${customerIdType}`;
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
                </div>
                {
                    !progress ? <LinearProgress color="success" /> : <div />
                }
            </Card>
            {
                resultRows != null && resultRows.length > 0 ? <CustomerHistoryList row={resultRows} onSearch={onSearch}/> : <div />
            }
        </div>
    );
}

export default CustomerHistory;