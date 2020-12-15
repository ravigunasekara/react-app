import React, { Component } from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import ToDoListService from '../service/ToDoListService'
import DatePicker from 'react-datepicker/dist/react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

class Item extends Component {

    constructor(props) {
        super(props)
        this.state = {
            id: this.props.match.params.itemId,
            listId: this.props.match.params.listId,
            item: this.props.location.data
        }
        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)
    }

    componentDidMount() {
        if (this.state.id == -1) {
            return
        }
    }

    onSubmit(values) {

        if (this.state.id == -1) {
            console.log("Calling create " + values);
            ToDoListService.createItem(this.state.listId, values.item)
                .then(() => this.props.history.push(`/list/${this.state.listId}`))
        } else {
            console.log("Calling update " + values);
            ToDoListService.updateItem(this.state.listId, values.item)
                .then(() => this.props.history.push(`/list/${this.state.listId}`))
        }

        console.log(values);
    }

    validate(values) {
        let errors = {}
        if (!values.description) {
            errors.description = 'Enter a Description'
        }
        return errors
    }

    render() {
        let { id, item } = this.state
        return (
            <div>
                <h3>Item</h3>

                <div className="container">
                    <Formik
                        initialValues={{ id, item }}
                        onSubmit={this.onSubmit}
                        enableReinitialize={true} >
                        {
                            (props) => (
                                <Form>
                                    <ErrorMessage name="errors.description" component="div"
                                        className="alert alert-warning" />
                                    <fieldset className="form-group">
                                        <label>Id</label>
                                        <Field className="form-control" type="text" name="id" disabled />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Description</label>
                                        <Field className="form-control" type="text" required name="item.description" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Status</label>
                                        <Field className="form-control" component="select" required name="item.status">
                                            <option value="COMPLETED">COMPLETED</option>
                                            <option value="ACTIVE">ACTIVE</option>
                                        </Field>
                                        {/* <label>
                                            Status:
                                             <select value='ACTIVE' onChange="item.status">
                                                <option value="ACTIVE">ACTIVE</option>
                                                <option value="COMPLETED">COMPLETED</option>
                                            </select>
                                        </label> */}
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Date </label>
                                        <Field name="item.date">
                                            {({ form, field }) => {
                                                const { setFieldValue } = form
                                                const { value } = new Date(field)
                                                return (
                                                    <DatePicker
                                                        id={"item.date"}
                                                        {...field}
                                                        {...props}
                                                        selected={value}
                                                        onChange={val => setFieldValue("item.date",
                                                            new Date(val.getTime() - (val.getTimezoneOffset() * 60000))
                                                                .toISOString().split("T")[0])}
                                                    />
                                                )
                                            }}
                                        </Field>
                                    </fieldset>
                                    <button className="btn btn-success" type="submit">Save</button>
                                </Form>
                            )
                        }
                    </Formik>

                </div>
            </div>
        )
    }

}

export default Item