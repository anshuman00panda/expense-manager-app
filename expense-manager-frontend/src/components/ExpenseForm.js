import * as Yup from 'yup';
import Formik, { ErrorMessage, Field } from 'formik';

const ExpenseForm = ({ onSaveExpense }) =>{

    const initialValues = {
        title: '',
        amount: '',
        category: '',
        date: '',
    };

    const validationSchema = Yup.object({
        title: Yup.string().required('Title is required.'),
        amount: Yup.number().required('Amount is required').positive('Amount must be positive'),
        category: Yup.string().required('Category is required'),
        date: Yup.date().required('Date is required')
    });
    
    const handleSubmit = (values, { resetForm }) => {
        onSaveExpense(values);
        resetForm();
    };

    return(
        <Formik 
            initialValues = {initialValues}
            validationSchema = {validationSchema}
            onSubmit = {handleSubmit}
        >
            {() => (
                <Form>
                    <div>
                        <label>Title</label>
                        <Field type="text" name="title" />
                        <ErrorMessage name='title' component='div' style={{ color: 'red'}} />
                    </div>
                    <div> 
                        <label>Amount</label>
                        <Field type="number" name="amount" />
                        <ErrorMessage name='amount' component="div" style={{ color: 'red'}}/>
                    </div>
                    <div>
                        <label>Category</label>
                        <Field type="text" name="category" />
                        <ErrorMessage name='category' component="div" style ={{ color: 'red'}} />
                    </div>
                    <div> 
                        <label>Date</label>
                        <Field type="date" name="date"/>
                        <ErrorMessage name='date' component="div" style={{ color:'red'}}/>
                    </div>

                    <button type='submit'>Add Expense</button>
                </Form>

            )}
        </Formik>
    );
} 
export default ExpenseForm;
