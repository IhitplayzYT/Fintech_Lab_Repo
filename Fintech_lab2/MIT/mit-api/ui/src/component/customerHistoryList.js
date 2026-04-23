import * as React from 'react';
import { DataGrid } from '@mui/x-data-grid';
import Card from '@mui/material/Card';
import { IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import DialogTitle from '@mui/material/DialogTitle';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import axios from 'axios';
import Button from '@mui/material/Button';
import EditIcon from '@mui/icons-material/Edit';
import DialogContent from '@mui/material/DialogContent';
import Slide from '@mui/material/Slide';
import CloseOutlinedIcon from '@mui/icons-material/CloseOutlined';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import UpdateUserDtl from './UpdateUserDtl';

const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction="up" ref={ref} {...props} />;
});
export default function CustomerHistoryList(props) {
  const { row, onSearch } = props;
  const [open, setOpen] = React.useState(false);
  const [id, setID] = React.useState();
  const [rowData, setRowData] = React.useState();
  const [updateOpen, setUpdateOpen] = React.useState(false);
  const handleClose = () => {
    setOpen(false);
    setUpdateOpen(false);
  };
  const onConfirmClick = async () => {
    try {
      const hostUrl = "http://localhost:8081";
      let uri = `${hostUrl}/api/customer/deleteCustomerDetails/${id}`;
      console.log(id);
      const response = await axios.delete(uri);
      if (response.data != null) {
        setID(0);
        setOpen(false);
        onSearch();
      }
    } catch (error) {
      setOpen(false);
    }
  };
  const onEditClick = async (row) => {
    setRowData(row);
    setUpdateOpen(true);
    console.log("Dinesh", row);
  }
  const onUpdateClick = async(data)=>{
    try {
      let customerID=rowData.customerId;
      const hostUrl = "http://localhost:8081";
      let uri = `${hostUrl}/api/customer/updateCustomerDetails/${customerID}`;
      const response = await axios.put(uri,data);
      if (response.data != null) {
        setUpdateOpen(false);
        onSearch();
      }
    } catch (error) {
      setUpdateOpen(false);
    }
  }
  const columns = [
    { field: 'id', headerName: 'Id' },
    { field: 'customerId', headerName: 'CustomerID' },
    { field: 'customerName', headerName: 'Customer Name', width: 250 },
    { field: 'customerStatus', headerName: 'Status', width: 250 },
    { field: 'customerGender', headerName: 'Gender', width: 250 },
    { field: 'customerPreferredLanguage', headerName: 'Preferred Language', width: 250 },
    { field: 'customerCountryOrigin', headerName: 'Country Origin', width: 250 }
  ];
  return (
    <div>
      <Card elevation={0} style={{ width: '93vw', padding: '10px', marginBottom: '20px', marginTop: '10px' }}>
        <DataGrid headerClassName="bold-header"
          rows={row}
          columns={columns}
          initialState={{
            pagination: {
              paginationModel: {
                pageSize: 50,
              },
            },
          }}
          pageSizeOptions={[10, 50, 100]}
          disableRowSelectionOnClick
        />
      </Card>
    </div>
  );
}