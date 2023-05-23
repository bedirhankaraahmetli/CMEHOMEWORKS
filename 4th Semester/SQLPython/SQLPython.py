import csv
import json
import operator


# 2020510046 Vedat Kara
# 2020510047 Bedirhan Karaahmetli

# Project is working well for select and insert operations
# Delete operation is working slowly for some conditions

def is_a_column(string):  # checks if given string is a column in giving csv file
    if string == "id" or string == "name" or string == "lastname" or string == "email" or string == "grade":
        return True
    else:
        return False


def operations(op, column, a, b, data_to_write, lst, info, selected_list):  # operations for select
    if op == "=":
        if operator.eq(a, b):
            lst.append(data_to_write)
            selected_list.append(info)
    elif op == "!=":
        if operator.ne(a, b):
            lst.append(data_to_write)
            selected_list.append(info)
    elif op == "<" and (column == "id" or column == "grade"):
        if operator.lt(a, b):
            lst.append(data_to_write)
            selected_list.append(info)
    elif op == ">" and (column == "id" or column == 'grade'):
        if operator.gt(a, b):
            lst.append(data_to_write)
            selected_list.append(info)
    elif op == "<=" and (column == "id" or column == "grade"):
        if operator.le(a, b):
            lst.append(data_to_write)
            selected_list.append(info)
    elif op == ">=" and (column == "id" or column == "grade"):
        if operator.ge(a, b):
            lst.append(data_to_write)
            selected_list.append(info)
    elif op == "!<" and (column == "id" or column == "grade"):
        if operator.ge(a, b):
            lst.append(data_to_write)
            selected_list.append(info)
    elif op == "!>" and (column == "id" or column == "grade"):
        if operator.le(a, b):
            lst.append(data_to_write)
            selected_list.append(info)
    else:
        print("It seems that you may have tried to perform an operation other than"
              " '=' or '!=' on a column that contains a string value.")


def sort_list(lst, first_column, order):  # sorting given list by given first column by given order
    if order == "asc":
        if first_column == "id":
            lst.sort(key=lambda x: x["id"])
        elif first_column == "name":
            lst.sort(key=lambda x: x["name"])
        elif first_column == "lastname":
            lst.sort(key=lambda x: x["lastname"])
        elif first_column == "email":
            lst.sort(key=lambda x: x["email"])
        elif first_column == "grade":
            lst.sort(key=lambda x: x["grade"])
    elif order == "dsc":
        if first_column == "id":
            lst.sort(reverse=True, key=lambda x: x["id"])
        elif first_column == "name":
            lst.sort(reverse=True, key=lambda x: x["name"])
        elif first_column == "lastname":
            lst.sort(reverse=True, key=lambda x: x["lastname"])
        elif first_column == "email":
            lst.sort(reverse=True, key=lambda x: x["email"])
        elif first_column == "grade":
            lst.sort(reverse=True, key=lambda x: x["grade"])


class Main:

    # Defining scheme for students table
    students_schema = [
        ("id", int),
        ("name", str),
        ("lastname", str),
        ("email", str),
        ("grade", int)]

    # Read the students data from the CSV file
    with open("students.csv", "r") as f:
        reader = csv.reader(f, delimiter=";")
        next(reader)  # Skip the header in csv
        students = []
        for row in reader:
            student = {}
            for i, value in enumerate(row):
                # Convert value to the appropriate type based on the schema
                col_name, col_type = students_schema[i]
                student[col_name] = col_type(value)
            students.append(student)

    # Sorting the students by id
    students.sort(key=lambda x: x["id"])

    query = " "  # initialize query
    temp = []  # create temp array
    selected = []  # list for selected entries
    selected2 = []  # list for selected entries 2
    select_temp = []  # list for selected one item in entries
    select_temp2 = []  # list for selected one item in entries 2
    temp = students  # fill temp list

    while query.lower() != "exit":  # Do not exit from the program until user desires.

        query = input("Enter your query: ")  # Getting query from user
        query_split = query.split(" ")  # Splitting the query by spaces
        COMMA = ","  # Defining comma
        select_temp.clear()  # Clearing list for selected one item in entries
        select_temp2.clear()  # Clearing list for selected one item in entries 2
        selected.clear()  # Clearing list for selected entries
        selected2.clear()  # Clearing list for selected entries 2
        columns = []  # List for hold the columns
        merged_list = []  # List for holding the merged entries

        if query_split[0].lower() == "select":  # SELECT operation
            if query_split[1].lower() == "all":  # column all
                columns = ["id", "name", "lastname", "email", "grade"]
            elif COMMA in query_split[1]:  # columns with commas
                columns = query_split[1].split(COMMA)
            elif is_a_column(query_split[1]):  # one column
                column = str(query_split[1])
                columns.append(column)
            if query_split[2].lower() == "from" \
                    and query_split[3].lower() == "students" \
                    and query_split[4].lower() == "where":

                operation = query_split[6]  # Taking first operation
                column_to_compare = query_split[5]  # Taking first column
                if column_to_compare == "id" or column_to_compare == "grade":
                    value_to_compare = int(query_split[7])
                else:
                    value_to_compare = query_split[7]
                    if '"' in value_to_compare or "'" in value_to_compare:
                        value_to_compare = value_to_compare[1:-1]

                column = columns[0]  # Keeping the first given column for use to sort
                if is_a_column(query_split[5]):  # Controlling the first given column is a column
                    for i in temp:  # Selecting for first part
                        student_value = i.get(column_to_compare)
                        data = i.get(column)
                        operations(operation, column_to_compare, student_value, value_to_compare,
                                   data, select_temp, i, selected)

                if len(query_split) > 8 and query_split[8].lower() == "and":  # Selecting after and part if any
                    column_to_compare_after_and = query_split[9]
                    operation_after_and = query_split[10]
                    value_to_compare_after_and = query_split[11]
                    if column_to_compare_after_and == "id" or column_to_compare_after_and == "grade":
                        value_to_compare_after_and = int(query_split[11])
                    else:
                        value_to_compare_after_and = query_split[11]
                        if "\"" in value_to_compare_after_and or "\'" in value_to_compare_after_and:
                            value_to_compare_after_and = value_to_compare_after_and[1:-1]
                    if is_a_column(column_to_compare_after_and):
                        for i in selected:
                            student_value = i.get(column_to_compare_after_and)
                            data = i.get(column)
                            operations(operation_after_and, column_to_compare_after_and, student_value,
                                       value_to_compare_after_and, data, select_temp2, i, selected2)

                    merged_list.clear()
                    for i in selected:
                        if i in selected2:
                            merged_list.append(i)  # Combining two lists that includes in both lists

                elif len(query_split) > 8 and query_split[8].lower() == "or":  # Selecting after or part if any
                    column_to_compare_after_or = query_split[9]
                    operation_after_or = query_split[10]
                    value_to_compare_after_or = query_split[11]
                    if column_to_compare_after_or == "id" or column_to_compare_after_or == "grade":
                        value_to_compare_after_or = int(query_split[11])
                    else:
                        value_to_compare_after_or = query_split[11]
                        if "\"" in value_to_compare_after_or or "\'" in value_to_compare_after_or:
                            value_to_compare_after_or = value_to_compare_after_or[1:-1]
                    if is_a_column(column_to_compare_after_or):
                        for i in temp:
                            student_value = i.get(column_to_compare_after_or)
                            data = i.get(column)
                            operations(operation_after_or, column_to_compare_after_or, student_value,
                                       value_to_compare_after_or, data, select_temp2, i, selected2)

                    merged_list = selected  # Create a copy of the first list
                    for i in selected2:
                        if not selected.__contains__(i):
                            merged_list.append(i)  # Combining two lists without duplication
                if (len(query_split) > 8 and query_split[8].lower() == "and") or (
                        len(query_split) > 8 and query_split[8].lower() == "or"):
                    sort_list(merged_list, columns[0], query_split[-1])
                    for x in merged_list:
                        for column in columns:
                            print(x.get(column), end=" ")
                        print(x, end=" \n")
                else:
                    sort_list(selected, columns[0], query_split[-1])
                    for x in selected:
                        for column in columns:
                            print(x.get(column), end=" ")
                        print("---", end=" ")
                        print(x, end=" \n")

        elif query_split[0].lower() == "delete":  # DELETE operation
            if query_split[1].lower() == "from":
                if query_split[2].lower() == "students":
                    if query_split[3].lower() == "where":
                        operation = query_split[5]
                        column_to_compare = query_split[4]
                        value_to_compare = query_split[6][1:-1]
                        if column_to_compare == "id" or column_to_compare == "grade":
                            value_to_compare = int(query_split[6])
                        else:
                            value_to_compare = query_split[6]
                            if '"' in value_to_compare or "'" in value_to_compare:
                                value_to_compare = value_to_compare[1:-1]

                        if is_a_column(column_to_compare):
                            for i in temp:
                                student_value = i.get(column_to_compare)
                                data = i.get(column_to_compare)
                                operations(operation, column_to_compare, student_value, value_to_compare,
                                           data, select_temp, i, selected)

                            if len(query_split) > 7 and query_split[7].lower() == "and":
                                column_to_compare_after_and = query_split[8]
                                operation_after_and = query_split[9]
                                value_to_compare_after_and = query_split[10]
                                if column_to_compare_after_and == "id" or column_to_compare_after_and == "grade":
                                    value_to_compare_after_and = int(query_split[10])
                                else:
                                    value_to_compare_after_and = query_split[10]
                                    if "\"" in value_to_compare_after_and or "\'" in value_to_compare_after_and:
                                        value_to_compare_after_and = value_to_compare_after_and[1:-1]
                                if is_a_column(column_to_compare_after_and):
                                    for i in selected:
                                        student_value = i.get(column_to_compare_after_and)
                                        data = i.get(column_to_compare_after_and)
                                        operations(operation_after_and, column_to_compare_after_and, student_value,
                                                   value_to_compare_after_and, data, select_temp2, i, selected2)

                                merged_list.clear()  # Create a copy of the first list
                                for i in selected:
                                    if i in selected2:
                                        merged_list.append(i)

                            elif len(query_split) > 7 and query_split[7].lower() == "or":
                                column_to_compare_after_or = query_split[8]
                                operation_after_or = query_split[9]
                                value_to_compare_after_or = query_split[10]
                                if column_to_compare_after_or == "id" or column_to_compare_after_or == "grade":
                                    value_to_compare_after_or = int(query_split[10])
                                else:
                                    value_to_compare_after_or = query_split[10]
                                    if "\"" in value_to_compare_after_or or "\'" in value_to_compare_after_or:
                                        value_to_compare_after_or = value_to_compare_after_or[1:-1]
                                if is_a_column(column_to_compare_after_or):
                                    for i in temp:
                                        student_value = i.get(column_to_compare_after_or)
                                        data = i.get(column_to_compare_after_or)
                                        operations(operation_after_or, column_to_compare_after_or, student_value,
                                                   value_to_compare_after_or, data, select_temp2, i, selected2)

                                merged_list = selected  # Create a copy of the first list
                                for i in selected2:
                                    if not selected.__contains__(i):
                                        merged_list.append(i)

                        if (len(query_split) > 7 and query_split[7].lower() == "and") or (
                                len(query_split) > 8 and query_split[7].lower() == "or"):
                            for x in merged_list:
                                temp.remove(x)
                        else:
                            for x in selected:
                                temp.remove(x)

                        for i in temp:
                            print(i)

        elif query_split[0].lower() == "insert":
            student_info = query_split[3][7:len(query_split[3])]
            student_info_split = student_info.split(COMMA)
            if len(student_info_split) == 5:
                new_student = {
                    "id": student_info_split[0],
                    "name": student_info_split[1],
                    "lastname": student_info_split[2],
                    "email": student_info_split[3],
                    "grade": student_info_split[4]
                }
                temp.append(new_student)
                print(temp)
            else:
                print("You entered wrong number of values!")

    file_path = "data.json"
    with open(file_path, "w") as json_file:
        json.dump(temp, json_file)