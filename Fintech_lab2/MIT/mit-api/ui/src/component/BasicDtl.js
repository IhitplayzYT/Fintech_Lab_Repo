import { Button, TextField } from '@mui/material';
import * as React from 'react';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
function BasicDtl(props) {
    const { onNextClick } = props;
    const [disableButton, setDisableButton] = React.useState(false);
    const [firstName, setFirstName] = React.useState();
    const [lastName, setlastName] = React.useState();
    const [dob, setDOB] = React.useState();
    const [middleName, setMiddleName] = React.useState();
    const [gender, setGender] = React.useState('Male');
    const onFirstNamevalueChange = async (value) => {
        setFirstName(value.target.value)
    }
    const onLastNamevalueChange = async (value) => {
        setlastName(value.target.value)
    }
    const onDOBvalueChange = async (value) => {
        setDOB(value.target.value)
    }
    const onMiddleNamevalueChange = async (value) => {
        setMiddleName(value.target.value)
    }
    const onNxtClick = async () => {
        let data = {
            firstName: firstName,
            lastName: lastName,
            middleName: middleName,
            dob: dob,
            gender:gender,
            index: 1
        }
        onNextClick(data)
    }
    return (
        <div>
            <TextField label='First Name'
                value={firstName}
                onChangeCapture={onFirstNamevalueChange}
                onChange={onFirstNamevalueChange}
                variant='outlined'
                size='small'
                type='text'
                style={{ display: 'flex', borderColor: '#000a00', borderRadius: 16, marginTop: '20px', marginLeft: '50px', marginRight: '50px', marginBottom: '20px' }}
                color='secondary'
            />
            <TextField label='Middle Name'
                value={middleName}
                onChangeCapture={onMiddleNamevalueChange}
                onChange={onMiddleNamevalueChange}
                variant='outlined'
                size='small'
                type='text'
                style={{ display: 'flex', borderColor: '#000a00', borderRadius: 16, marginTop: '20px', marginLeft: '50px', marginRight: '50px', marginBottom: '20px' }}
                color='secondary'
            />
            <TextField label='Last Name'
                value={lastName}
                onChangeCapture={onLastNamevalueChange}
                onChange={onLastNamevalueChange}
                variant='outlined'
                size='small'
                type='text'
                style={{ display: 'flex', borderColor: '#000a00', borderRadius: 16, marginTop: '20px', marginLeft: '50px', marginRight: '50px', marginBottom: '20px' }}
                color='secondary'
            />
            <TextField label=''
                helperText=''
                value={dob}
                onChangeCapture={onDOBvalueChange}
                onChange={onDOBvalueChange}
                variant='outlined'
                size='small'
                type='date'
                style={{ display: 'flex', borderColor: '#000a00', borderRadius: 16, marginTop: '20px', marginLeft: '50px', marginRight: '50px', marginBottom: '20px' }}
                color='secondary'
            />
            <FormControl style={{ width: '83%', marginRight: '20px' }}>
                <Select size='small'
                    value={gender}
                    variant='outlined'
                    sx={{ width: '100%', mr: 0 }}
                    style={{ display: 'flex', borderColor: '#000a00', borderRadius: 8, marginTop: '20px', marginLeft: '50px', marginRight: '50px', marginBottom: '20px' }}
                    color='secondary'
                    onChange={(value) => setGender(value.target.value)}
                >
                    {
                        ['Male', 'Female', 'Other'].map((value) => (
                            <MenuItem value={value}>{value}</MenuItem>
                        ))
                    }
                </Select>
            </FormControl>
            <div style={{ display: 'flex' }}>
                <Button disabled={disableButton} disableElevation style={{ marginLeft: '50px', marginTop: '30px', alignItems: 'end', color: disableButton ? '#b3b3b3' : '#000a00', borderRadius: 8, backgroundColor: disableButton ? '#f2f2f2' : '#4bcd3e', textTransform: 'initial', fontWeight: 'bold' }}
                    variant='contained' onClick={onNxtClick} sx={{ height: '100%', width: '10vw' }}>
                    Next
                </Button>
            </div>
        </div>
    );
}
export default BasicDtl;