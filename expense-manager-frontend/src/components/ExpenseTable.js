const ExpenseTable = ({ expenses, onEdit, onDelete}) => {
    return(
        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Category</th>
                    <th>Amount</th>
                    <th>Date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {expenses.map((expense) => {
                    <tr key={expense.id}>
                        <td>{expense.title}</td>
                        <td>{expense.category}</td>
                        <td>{expense.amount}</td>
                        <td>{expense.date}</td>
                        <td>
                            <button onClick={() => onEdit(expense)}>Edit</button>
                            <button onClick={() => onDelete(expense.id)}>Delete</button>
                        </td>
                    </tr>
                })}
            </tbody>
        </table>
    );
};
export default ExpenseTable;
