/*Copyright (C) 2014  JD Software, Inc.

  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU Affero General Public License as
  published by the Free Software Foundation, either version 3 of the
  License, or (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Affero General Public License for more details.

  You should have received a copy of the GNU Affero General Public License
  along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.jd.survey.domain.survey;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.CurrencyValidator;
import org.apache.commons.validator.routines.DateValidator;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jd.survey.domain.settings.DataSetItem;
import com.jd.survey.domain.settings.Question;
import com.jd.survey.domain.settings.QuestionColumnLabel;
import com.jd.survey.domain.settings.QuestionOption;

public class QuestionAnswerValidator implements Validator {

    // ~ Static fields/initializers
    // =========================================================

    private static final Log log = LogFactory.getLog(QuestionAnswerValidator.class);

    public final Integer ONE_BYTE = 1048576;

    // ~ Default Instance fields
    // ============================================================

    private final String dateFormat;

    private final String validContentTypes;

    private final String validImageTypes;

    private final Integer maximunFileSize;

    private final String invalidContentMessage;

    private final String invalidFileSizeMessage;

    // ~ Instance fields
    // ====================================================================

    // ~ Constructors
    // =======================================================================

    public QuestionAnswerValidator(String dateFormat, String validcontentTypes, String validImageTypes,
            Integer maximunFileSize, String invalidContentMessage, String invalidFileSizeMessage) {
        super();
        this.dateFormat = dateFormat;
        this.validContentTypes = validcontentTypes.toLowerCase();
        this.validImageTypes = validImageTypes.toLowerCase();
        this.maximunFileSize = maximunFileSize;
        this.invalidContentMessage = invalidContentMessage;
        this.invalidFileSizeMessage = invalidFileSizeMessage;
    }

    // ~ Methods
    // ============================================================================

    // ~ Default Methods
    // ====================================================================

    @SuppressWarnings("rawtypes")
    @Override
    public boolean supports(Class clazz) {
        return QuestionAnswer.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        try {

            boolean isValid;
            String validationFieldName = "stringAnswerValue";
            QuestionAnswer questionAnswer = (QuestionAnswer) obj;
            Question question = questionAnswer.getQuestion();
            String valueToValidate;
            BigDecimalValidator bigDecimalValidator;
            int optionsCount;
            int rowCount;
            int columnCount;
            System.out.println("Question type: " + question.getType());
            if (question.getVisible()) {
                switch (question.getType()) {
                case YES_NO_DROPDOWN: // Yes No DropDown
                    break;
                case SHORT_TEXT_INPUT: // Short Text Input
                    // validate isRequired
                    valueToValidate = questionAnswer.getStringAnswerValue();
                    if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                        errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                        break;
                    }
                    // continue validation if value entered is not null or empty
                    if (valueToValidate != null && !valueToValidate.isEmpty()) {
                        // validate range
                        if (question.getIntegerMinimum() != null
                                && !GenericValidator.minLength(valueToValidate, question.getIntegerMinimum())) {
                            errors.rejectValue(validationFieldName, "field_length_min",
                                    new Object[] { question.getIntegerMinimum() },
                                    "텍스트의 길이가 " + question.getIntegerMinimum() + "를 초과해야 합니다.");
                            break;
                        }
                        if (question.getIntegerMaximum() != null
                                && !GenericValidator.maxLength(valueToValidate, question.getIntegerMaximum())) {
                            errors.rejectValue(validationFieldName, "field_length_max",
                                    new Object[] { question.getIntegerMaximum() },
                                    "텍스트의 길이가 " + question.getIntegerMaximum() + " 이하여야 합니다.");
                            break;
                        }

                        // validate regular expression
                        if (question.getRegularExpression() != null && !question.getRegularExpression().trim().isEmpty()
                                && !GenericValidator.matchRegexp(valueToValidate, question.getRegularExpression())) {
                            errors.rejectValue(validationFieldName, "field_invalid_type", "Invalid entry");
                            break;
                        }
                    }
                    break;
                case LONG_TEXT_INPUT: // Long Text Input
                    // validate isRequired
                    valueToValidate = questionAnswer.getStringAnswerValue();
                    if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                        errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                        break;
                    }
                    // continue validation if value entered is not null or empty
                    if (valueToValidate != null && !valueToValidate.isEmpty()) {
                        // validate range
                        if (question.getIntegerMinimum() != null
                                && !GenericValidator.minLength(valueToValidate, question.getIntegerMinimum())) {
                            errors.rejectValue(validationFieldName, "field_length_min",
                                    new Object[] { question.getIntegerMinimum() },
                                    "텍스트의 길이가 " + question.getIntegerMinimum() + "를 초과해야 합니다.");
                            break;
                        }
                        if (question.getIntegerMaximum() != null
                                && !GenericValidator.maxLength(valueToValidate, question.getIntegerMaximum())) {
                            errors.rejectValue(validationFieldName, "field_length_max",
                                    new Object[] { question.getIntegerMaximum() },
                                    "텍스트의 길이가 " + question.getIntegerMaximum() + " 이하여야 합니다.");
                            break;
                        }
                        // validate regular expression
                        if (question.getRegularExpression() != null && !question.getRegularExpression().trim().isEmpty()
                                && !GenericValidator.matchRegexp(valueToValidate, question.getRegularExpression())) {
                            errors.rejectValue(validationFieldName, "field_invalid_type", "Invalid entry");
                            break;
                        }
                    }
                    break;
                case HUGE_TEXT_INPUT: // Huge Text Input
                    // validate isRequired
                    valueToValidate = questionAnswer.getStringAnswerValue();
                    if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                        errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                        break;
                    }
                    // continue validation if value entered is not null or empty
                    if (valueToValidate != null && !valueToValidate.isEmpty()) {
                        // validate range
                        if (question.getIntegerMinimum() != null
                                && !GenericValidator.minLength(valueToValidate, question.getIntegerMinimum())) {
                            errors.rejectValue(validationFieldName, "field_length_min",
                                    new Object[] { question.getIntegerMinimum() },
                                    "텍스트의 길이가 " + question.getIntegerMinimum() + "를 초과해야 합니다.");
                            break;
                        }
                        if (question.getIntegerMaximum() != null
                                && !GenericValidator.maxLength(valueToValidate, question.getIntegerMaximum())) {
                            errors.rejectValue(validationFieldName, "field_length_max",
                                    new Object[] { question.getIntegerMaximum() },
                                    "텍스트의 길이가 " + question.getIntegerMaximum() + " 이하여야 합니다.");
                            break;
                        }
                        // validate regular expression
                        if (question.getRegularExpression() != null && !question.getRegularExpression().trim().isEmpty()
                                && !GenericValidator.matchRegexp(valueToValidate, question.getRegularExpression())) {
                            errors.rejectValue(validationFieldName, "field_invalid_type", "Invalid entry");
                            break;
                        }
                    }
                    break;
                case INTEGER_INPUT: // Integer Input
                    valueToValidate = questionAnswer.getStringAnswerValue();
                    // validate isRequired
                    if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                        errors.rejectValue(validationFieldName, "field_required", "This field is invalid");
                        break;
                    }
                    // continue validation if value entered is not null or empty
                    if (valueToValidate != null && !valueToValidate.isEmpty()) {
                        if (!GenericValidator.isInt(valueToValidate)) {
                            errors.rejectValue(validationFieldName, "field_invalid_integer", "This field is invalid");
                            break;
                        }

                        // validate range
                        if (question.getIntegerMinimum() != null
                                && !GenericValidator.minValue(Integer.parseInt(valueToValidate),
                                        question.getIntegerMinimum())) {

                            errors.rejectValue(validationFieldName, "field_value_min",
                                    new Object[] { question.getIntegerMinimum() },
                                    "값은 " + question.getIntegerMinimum() + "를 초과해야 합니다.");
                            break;
                        }
                        if (question.getIntegerMaximum() != null
                                && !GenericValidator.maxValue(Integer.parseInt(valueToValidate),
                                        question.getIntegerMaximum())) {
                            errors.rejectValue(validationFieldName, "field_value_max",
                                    new Object[] { question.getIntegerMaximum() },
                                    "값은 " + question.getIntegerMaximum() + " 이하여야 합니다.");
                            break;
                        }
                    }
                    questionAnswer.setLongAnswerValue(
                            valueToValidate != null && valueToValidate.length() > 0 ? Long.parseLong(valueToValidate)
                                    : null);
                    break;
                case CURRENCY_INPUT: // Currency Input
                    valueToValidate = questionAnswer.getStringAnswerValue();
                    // validate isRequired
                    if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                        errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                        break;
                    }

                    // continue validation if value entered is not null or empty
                    if (valueToValidate != null && !valueToValidate.isEmpty()) {

                        CurrencyValidator currencyValidator = new CurrencyValidator(true, true);
                        if (!currencyValidator.isValid(valueToValidate, LocaleContextHolder.getLocale())) {
                            errors.rejectValue(validationFieldName, "field_invalid_currency",
                                    "통화 형식이 올바르지 않습니다. 정수로 입력바랍니다.");
                            break;
                        }

                        // removing all '$' and ',' from string prior to validating max and min
                        valueToValidate = valueToValidate.replaceAll("\\$", "");
                        valueToValidate = valueToValidate.replaceAll("￦", "");
                        valueToValidate = valueToValidate.replaceAll(",", "");

                        // validate range
                        if (question.getDecimalMinimum() != null
                                && !GenericValidator.minValue(Double.parseDouble(valueToValidate),
                                        question.getDecimalMinimum().doubleValue())) {
                            errors.rejectValue(validationFieldName, "field_value_min",
                                    new Object[] { question.getDecimalMinimum() },
                                    "값은 " + question.getDecimalMinimum() + "를 초과해야 합니다.");
                            break;
                        }
                        if (question.getDecimalMaximum() != null
                                && !GenericValidator.maxValue(Double.parseDouble(valueToValidate),
                                        question.getDecimalMaximum().doubleValue())) {
                            errors.rejectValue(validationFieldName, "field_value_max",
                                    new Object[] { question.getDecimalMaximum() },
                                    "값은 " + question.getDecimalMaximum() + " 이하여야 합니다.");
                            break;
                        }

                        questionAnswer.setBigDecimalAnswerValue(
                                currencyValidator.validate(valueToValidate, LocaleContextHolder.getLocale()));
                        questionAnswer.setStringAnswerValue(currencyValidator.format(
                                currencyValidator.validate(valueToValidate, LocaleContextHolder.getLocale()),
                                LocaleContextHolder.getLocale()));

                    }
                    break;

                case DECIMAL_INPUT: // Numeric Input (Decimal)
                    valueToValidate = questionAnswer.getStringAnswerValue();
                    // validate isRequired
                    if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                        errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                        break;
                    }
                    // continue validation if value entered is not null or empty
                    bigDecimalValidator = new BigDecimalValidator(true);
                    if (valueToValidate != null && !valueToValidate.isEmpty()) {
                        if (!bigDecimalValidator.isValid(valueToValidate, LocaleContextHolder.getLocale())) {
                            errors.rejectValue(validationFieldName, "field_invalid_decimal",
                                    "형식이 올바르지 않습니다. 소수점을 포함한 숫자로 입력바랍니다.");
                            break;
                        } else {
                            questionAnswer.setStringAnswerValue(bigDecimalValidator.format(
                                    bigDecimalValidator.validate(valueToValidate, LocaleContextHolder.getLocale()),
                                    LocaleContextHolder.getLocale()));
                        }

                        // removing all commas from string prior to validating max and min
                        valueToValidate = valueToValidate.replaceAll(",", "");
                        // validate range
                        if (question.getDecimalMinimum() != null
                                && !GenericValidator.minValue(Double.parseDouble(valueToValidate),
                                        question.getDecimalMinimum().doubleValue())) {
                            errors.rejectValue(validationFieldName, "field_value_min",
                                    new Object[] { question.getDecimalMinimum() },
                                    "값은 " + question.getDecimalMinimum() + "를 초과해야 합니다.");
                            break;
                        }
                        if (question.getDecimalMaximum() != null
                                && !GenericValidator.maxValue(Double.parseDouble(valueToValidate),
                                        question.getDecimalMaximum().doubleValue())) {
                            errors.rejectValue(validationFieldName, "field_value_max",
                                    new Object[] { question.getDecimalMaximum() },
                                    "값은 " + question.getDecimalMaximum() + "보아 작아야 합니다.");
                            break;
                        }
                    }
                    questionAnswer.setBigDecimalAnswerValue(valueToValidate.trim().length() > 0
                            ? bigDecimalValidator.validate(valueToValidate, LocaleContextHolder.getLocale())
                            : null);
                    break;
                case DATE_INPUT: // Date Input
                    valueToValidate = questionAnswer.getStringAnswerValue();
                    // validate isRequired
                    if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                        errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                        break;
                    }

                    // validate type if value entered is not null
                    if (valueToValidate != null && !valueToValidate.isEmpty()) {
                        if (!GenericValidator.isDate(valueToValidate, dateFormat, true)) {
                            errors.rejectValue(validationFieldName, "field_invalid_date",
                                    "날짜 형식이 올바르지 않습니다. YYYY-MM-DD 형식으로 입력바랍니다.");
                            break;
                        }
                        if (question.getDateMinimum() != null && (DateValidator.getInstance().validate(valueToValidate)
                                .compareTo(question.getDateMinimum()) <= 0)) {
                            errors.rejectValue(validationFieldName, "field_date_min",
                                    new Object[] { question.getDateMinimum() },
                                    question.getDateMinimum() + "이후 날짜여야 합니다.");
                            break;
                        }
                        if (question.getDateMaximum() != null && (DateValidator.getInstance().validate(valueToValidate)
                                .compareTo(question.getDateMaximum()) >= 0)) {
                            errors.rejectValue(validationFieldName, "field_date_max",
                                    new Object[] { question.getDateMaximum() },
                                    question.getDateMaximum() + "이전 날짜여야 합니다.");
                            break;
                        }
                    }

                    questionAnswer.setDateAnswerValue(
                            valueToValidate.trim().length() > 0 ? DateValidator.getInstance().validate(valueToValidate)
                                    : null);
                    break;
                case SINGLE_CHOICE_DROP_DOWN: // Single choice Drop Down
                    valueToValidate = questionAnswer.getStringAnswerValue();
                    isValid = false;
                    // validate isRequired
                    if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                        errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                        break;
                    }
                    // verify that the value exists in the options
                    for (QuestionOption option : question.getOptions()) {
                        if (option.getValue().equalsIgnoreCase(valueToValidate)) {
                            isValid = true;
                            break;
                        }
                    }
                    if (!isValid && !GenericValidator.isBlankOrNull(valueToValidate)) {
                        errors.rejectValue(validationFieldName, "field_invalid_type", "Invalid Option");
                        break;
                    }

                    break;
                case MULTIPLE_CHOICE_CHECKBOXES: // Multiple Choice Checkboxes
                    // validate isRequired
                    isValid = false;
                    optionsCount = questionAnswer.getQuestion().getOptions().size();
                    if (question.getRequired()) {
                        for (int i = 0; i < questionAnswer.getIntegerAnswerValuesArray().length; i++) {
                            if (questionAnswer.getIntegerAnswerValuesArray()[i] != null
                                    && questionAnswer.getIntegerAnswerValuesArray()[i] <= optionsCount) {
                                // one element was checked
                                isValid = true;
                                break;
                            }
                        }
                        if (!isValid) {
                            errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                            break;
                        }
                    }

                    isValid = true;
                    // verify that the value exists in the options range
                    for (int i = 0; i < questionAnswer.getIntegerAnswerValuesArray().length; i++) {
                        if (questionAnswer.getIntegerAnswerValuesArray()[i] != null
                                && questionAnswer.getIntegerAnswerValuesArray()[i] > optionsCount) {
                            // one element was checked
                            isValid = false;
                            break;
                        }
                    }
                    if (!isValid) {
                        errors.rejectValue(validationFieldName, "field_invalid_type", "Invalid Option");
                        break;
                    }

                    break;
                case DATASET_DROP_DOWN: // Dataset DropDown
                    valueToValidate = questionAnswer.getStringAnswerValue();
                    isValid = false;
                    // validate isRequired
                    if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                        errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                        break;
                    }
                    // verify that the value exists in the dataset

                    for (DataSetItem dataSetItem : question.getDataSetItems()) {
                        if (dataSetItem.getValue().equalsIgnoreCase(valueToValidate)) {
                            isValid = true;
                            break;
                        }
                    }
                    if (!isValid && !GenericValidator.isBlankOrNull(valueToValidate)) {
                        errors.rejectValue(validationFieldName, "field_invalid_type", "Invalid Option");
                        break;
                    }

                    break;

                case SINGLE_CHOICE_RADIO_BUTTONS: // Single Choice Radio Buttons
                    isValid = false;
                    valueToValidate = questionAnswer.getStringAnswerValue();
                    if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                        errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                        break;
                    }

                    // verify that the value exists in the options
                    for (QuestionOption option : question.getOptions()) {
                        if (option.getValue().equalsIgnoreCase(valueToValidate)) {
                            isValid = true;
                            break;
                        }
                    }
                    if (!isValid && !GenericValidator.isBlankOrNull(valueToValidate)) {
                        errors.rejectValue(validationFieldName, "field_invalid_type", "Invalid Option");
                        break;
                    }

                    break;

                case YES_NO_DROPDOWN_MATRIX:// Yes No DropDown Matrix
                    break;
                case SHORT_TEXT_INPUT_MATRIX:// Short Text Input Matrix
                    rowCount = questionAnswer.getQuestion().getRowLabels().size();
                    columnCount = questionAnswer.getQuestion().getColumnLabels().size();
                    for (int r = 1; r <= rowCount; r++) {
                        for (int c = 1; c <= columnCount; c++) {
                            valueToValidate = questionAnswer.getStringAnswerValuesMatrix()[r - 1][c - 1];
                            validationFieldName = "stringAnswerValuesMatrix[" + (r - 1) + "][" + (c - 1) + "]";
                            // validate isRequired
                            if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                                errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                                continue;
                            }
                            // continue validation if value entered is not null or empty
                            if (valueToValidate != null && !valueToValidate.isEmpty()) {
                                // validate range
                                if (question.getIntegerMinimum() != null
                                        && !GenericValidator.minLength(valueToValidate, question.getIntegerMinimum())) {
                                    errors.rejectValue(validationFieldName, "field_length_min",
                                            new Object[] { question.getIntegerMinimum() },
                                            "텍스트의 길이가 " + question.getIntegerMinimum() + "를 초과해야 합니다.");
                                    continue;
                                }
                                if (question.getIntegerMaximum() != null
                                        && !GenericValidator.maxLength(valueToValidate, question.getIntegerMaximum())) {
                                    errors.rejectValue(validationFieldName, "field_length_max",
                                            new Object[] { question.getIntegerMaximum() },
                                            "텍스트의 길이가 " + question.getIntegerMaximum() + " 이하여야 합니다.");
                                    continue;
                                }

                                // validate regular expression
                                if (question.getRegularExpression() != null
                                        && !question.getRegularExpression().trim().isEmpty() && !GenericValidator
                                                .matchRegexp(valueToValidate, question.getRegularExpression())) {
                                    errors.rejectValue(validationFieldName, "field_invalid_type", "Invalid entry");
                                    continue;
                                }
                            }
                        }
                    }
                    break;
                case INTEGER_INPUT_MATRIX:// Integer Input Matrix
                    rowCount = questionAnswer.getQuestion().getRowLabels().size();
                    columnCount = questionAnswer.getQuestion().getColumnLabels().size();
                    for (int r = 1; r <= rowCount; r++) {
                        for (int c = 1; c <= columnCount; c++) {
                            valueToValidate = questionAnswer.getStringAnswerValuesMatrix()[r - 1][c - 1];
                            validationFieldName = "stringAnswerValuesMatrix[" + (r - 1) + "][" + (c - 1) + "]";

                            // validate isRequired
                            if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                                errors.rejectValue(validationFieldName, "field_required", "This field is invalid");
                                continue;
                            }

                            // continue validation if value entered is not null or empty
                            if (valueToValidate != null && !valueToValidate.isEmpty()) {
                                if (!GenericValidator.isInt(valueToValidate)) {
                                    errors.rejectValue(validationFieldName, "field_invalid_integer",
                                            "This field is invalid");
                                    continue;
                                }

                                // validate range
                                if (question.getIntegerMinimum() != null
                                        && !GenericValidator.minValue(Integer.parseInt(valueToValidate),
                                                question.getIntegerMinimum())) {
                                    errors.rejectValue(validationFieldName, "field_value_min",
                                            new Object[] { question.getIntegerMinimum() },
                                            "값은 " + question.getIntegerMinimum() + "를 초과해야 합니다.");
                                    continue;
                                }
                                if (question.getIntegerMaximum() != null
                                        && !GenericValidator.maxValue(Integer.parseInt(valueToValidate),
                                                question.getIntegerMaximum())) {
                                    errors.rejectValue(validationFieldName, "field_value_max",
                                            new Object[] { question.getIntegerMaximum() },
                                            "값은 " + question.getIntegerMaximum() + " 이하여야 합니다.");
                                    continue;
                                }
                            }
                            questionAnswer.getLongAnswerValuesMatrix()[r - 1][c - 1] = (valueToValidate != null
                                    && valueToValidate.length() > 0 ? Long.parseLong(valueToValidate) : null);
                        }
                    }
                    break;
                case CURRENCY_INPUT_MATRIX:// Currency Input Matrix NEED TO ADD MAX
                                           // MIN???????????????????????????????????????????????????
                    rowCount = questionAnswer.getQuestion().getRowLabels().size();
                    columnCount = questionAnswer.getQuestion().getColumnLabels().size();
                    for (int r = 1; r <= rowCount; r++) {
                        for (int c = 1; c <= columnCount; c++) {
                            System.out.println("ROW: " + r + " / COL: " + c
                                    + " -----------------------------------------------------------------------------------------");
                            valueToValidate = questionAnswer.getStringAnswerValuesMatrix()[r - 1][c - 1];
                            validationFieldName = "stringAnswerValuesMatrix[" + (r - 1) + "][" + (c - 1) + "]";
                            // validate isRequired
                            if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                                errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                                continue;
                            }

                            // continue validation if value entered is not null or empty
                            if (valueToValidate != null && !valueToValidate.isEmpty()) {
                                CurrencyValidator currencyValidator = new CurrencyValidator(true, true);
                                if (!currencyValidator.isValid(valueToValidate, LocaleContextHolder.getLocale())) {
                                    errors.rejectValue(validationFieldName, "field_invalid_type",
                                            "통화 형식이 올바르지 않습니다. 정수로 입력바랍니다.");
                                    continue;
                                } else {
                                    questionAnswer.getBigDecimalAnswerValuesMatrix()[r - 1][c - 1] = currencyValidator
                                            .validate(valueToValidate, LocaleContextHolder.getLocale());
                                    questionAnswer.getStringAnswerValuesMatrix()[r - 1][c - 1] = currencyValidator
                                            .format(currencyValidator.validate(valueToValidate,
                                                    LocaleContextHolder.getLocale()), LocaleContextHolder.getLocale());
                                }
                                // removing all '$' and ',' from string prior to validating max and min
                                valueToValidate = valueToValidate.replaceAll("\\$", "");
                                valueToValidate = valueToValidate.replaceAll(",", "");

                                // Validating max and min
                                if (question.getDecimalMinimum() == null) {
                                    System.out.println(
                                            "MIN IS NULL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                }
                                if (question.getDecimalMinimum() != null
                                        && !GenericValidator.minValue(Double.parseDouble(valueToValidate),
                                                question.getDecimalMinimum().doubleValue())) {
                                    System.out.println(validationFieldName
                                            + "MIN ##################################################################################");
                                    errors.rejectValue(validationFieldName, "field_value_min",
                                            new Object[] { question.getDecimalMinimum() },
                                            "값은 " + question.getDecimalMinimum() + "를 초과해야 합니다.");
                                    continue;
                                }
                                if (question.getDecimalMaximum() == null) {
                                    System.out.println(
                                            "MAX IS NULL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                }

                                if (question.getDecimalMaximum() != null
                                        && !GenericValidator.maxValue(Double.parseDouble(valueToValidate),
                                                question.getDecimalMaximum().doubleValue())) {
                                    System.out.println(validationFieldName
                                            + "MAX ########################################################################################################");
                                    errors.rejectValue(validationFieldName, "field_value_max",
                                            new Object[] { question.getDecimalMaximum() },
                                            "값은 " + question.getDecimalMaximum() + " 이하여야 합니다.");
                                    continue;
                                }
                            }
                        }
                    }
                    break;

                case DECIMAL_INPUT_MATRIX:// Decimal Input Matrix
                    rowCount = questionAnswer.getQuestion().getRowLabels().size();
                    columnCount = questionAnswer.getQuestion().getColumnLabels().size();
                    for (int r = 1; r <= rowCount; r++) {
                        for (int c = 1; c <= columnCount; c++) {
                            valueToValidate = questionAnswer.getStringAnswerValuesMatrix()[r - 1][c - 1];
                            validationFieldName = "stringAnswerValuesMatrix[" + (r - 1) + "][" + (c - 1) + "]";
                            // validate isRequired
                            if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                                errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                                continue;
                            }

                            // continue validation if value entered is not null or empty
                            bigDecimalValidator = new BigDecimalValidator(true);
                            if (valueToValidate != null && !valueToValidate.isEmpty()) {
                                if (!bigDecimalValidator.isValid(valueToValidate, LocaleContextHolder.getLocale())) {
                                    errors.rejectValue(validationFieldName, "field_invalid_type",
                                            "형식이 올바르지 않습니다. 소수점을 포함한 숫자로 입력바랍니다.");
                                    continue;
                                } else {
                                    questionAnswer
                                            .setStringAnswerValue(bigDecimalValidator.format(
                                                    bigDecimalValidator.validate(valueToValidate,
                                                            LocaleContextHolder.getLocale()),
                                                    LocaleContextHolder.getLocale()));
                                }

                                // validate range
                                if (question.getDecimalMinimum() != null
                                        && !GenericValidator.minValue(Double.parseDouble(valueToValidate),
                                                question.getDecimalMinimum().doubleValue())) {
                                    errors.rejectValue(validationFieldName, "field_value_min",
                                            new Object[] { question.getDecimalMinimum() },
                                            "값은 " + question.getDecimalMinimum() + "를 초과해야 합니다.");
                                    continue;
                                }
                                if (question.getDecimalMaximum() != null
                                        && !GenericValidator.maxValue(Double.parseDouble(valueToValidate),
                                                question.getDecimalMaximum().doubleValue())) {
                                    errors.rejectValue(validationFieldName, "field_value_max",
                                            new Object[] { question.getDecimalMaximum() },
                                            "값은 " + question.getDecimalMaximum() + " 이하여야 합니다.");
                                    continue;
                                }
                            }
                            questionAnswer.getBigDecimalAnswerValuesMatrix()[r - 1][c
                                    - 1] = (valueToValidate.trim().length() > 0 ? bigDecimalValidator
                                            .validate(valueToValidate, LocaleContextHolder.getLocale()) : null);

                        }
                    }
                    break;
                case DATE_INPUT_MATRIX:// Date Input Matrix
                    rowCount = questionAnswer.getQuestion().getRowLabels().size();
                    columnCount = questionAnswer.getQuestion().getColumnLabels().size();
                    for (int r = 1; r <= rowCount; r++) {
                        for (int c = 1; c <= columnCount; c++) {
                            valueToValidate = questionAnswer.getStringAnswerValuesMatrix()[r - 1][c - 1];
                            validationFieldName = "stringAnswerValuesMatrix[" + (r - 1) + "][" + (c - 1) + "]";

                            // validate isRequired
                            if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                                errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                                continue;
                            }

                            // validate type if value entered is not null
                            if (valueToValidate != null && !valueToValidate.isEmpty()) {
                                if (!GenericValidator.isDate(valueToValidate, dateFormat, true)) {
                                    errors.rejectValue(validationFieldName, "field_invalid_date",
                                            "날짜 형식이 올바르지 않습니다. YYYY-MM-DD 형식으로 입력바랍니다.");
                                    continue;
                                }
                                if (question.getDateMinimum() != null && (DateValidator.getInstance()
                                        .validate(valueToValidate).compareTo(question.getDateMinimum()) <= 0)) {
                                    errors.rejectValue(validationFieldName, "field_date_min",
                                            new Object[] { question.getDateMinimum() },
                                            question.getDateMinimum() + "이후 날짜여야 합니다.");
                                    continue;
                                }
                                if (question.getDateMaximum() != null && (DateValidator.getInstance()
                                        .validate(valueToValidate).compareTo(question.getDateMaximum()) >= 0)) {
                                    errors.rejectValue(validationFieldName, "field_date_max",
                                            new Object[] { question.getDateMaximum() },
                                            question.getDateMaximum() + "이전 날짜여야 합니다.");
                                    continue;
                                }
                            }
                            questionAnswer.getDateAnswerValuesMatrix()[r - 1][c
                                    - 1] = (valueToValidate.trim().length() > 0
                                            ? DateValidator.getInstance().validate(valueToValidate)
                                            : null);
                        }
                    }
                    break;

                case COMPLEX_INPUT_MATRIX:// Complex Input Matrix
                    rowCount = questionAnswer.getQuestion().getRowLabels().size();
                    columnCount = questionAnswer.getQuestion().getColumnLabels().size();
                    for (int r = 1; r <= rowCount; r++) {
                        for (int c = 1; c <= columnCount; c++) {
                            valueToValidate = questionAnswer.getStringAnswerValuesMatrix()[r - 1][c - 1];
                            validationFieldName = "stringAnswerValuesMatrix[" + (r - 1) + "][" + (c - 1) + "]";

                            // validate isRequired
                            if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                                errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                                continue;
                            }

                            for (QuestionColumnLabel column : questionAnswer.getQuestion().getColumnLabels()) {
                                if (column.getOrder() == c) {
                                    switch (column.getType()) {
                                    case SHORT_TEXT_INPUT:
                                        if (valueToValidate != null && !valueToValidate.isEmpty()) {

                                        }
                                        break;
                                    case INTEGER_INPUT:
                                        if (valueToValidate != null && !valueToValidate.isEmpty()) {
                                            if (!GenericValidator.isInt(valueToValidate)) {
                                                errors.rejectValue(validationFieldName, "field_invalid_integer",
                                                        "형식이 올바르지 않습니다. 정수로 입력바랍니다.");
                                                continue;
                                            }
                                        }
                                        questionAnswer.getLongAnswerValuesMatrix()[r - 1][c
                                                - 1] = (valueToValidate != null && valueToValidate.length() > 0
                                                        ? Long.parseLong(valueToValidate)
                                                        : null);
                                        break;
                                    case DECIMAL_INPUT:
                                        bigDecimalValidator = new BigDecimalValidator(true);
                                        if (valueToValidate != null && !valueToValidate.isEmpty()) {
                                            if (!bigDecimalValidator.isValid(valueToValidate,
                                                    LocaleContextHolder.getLocale())) {
                                                errors.rejectValue(validationFieldName, "field_invalid_type",
                                                        "형식이 올바르지 않습니다. 소수점을 포함한 숫자로 입력바랍니다.");
                                                continue;
                                            } else {
                                                questionAnswer.setStringAnswerValue(bigDecimalValidator.format(
                                                        bigDecimalValidator.validate(valueToValidate,
                                                                LocaleContextHolder.getLocale()),
                                                        LocaleContextHolder.getLocale()));
                                            }
                                        }
                                        questionAnswer.getBigDecimalAnswerValuesMatrix()[r - 1][c
                                                - 1] = (valueToValidate.trim().length() > 0 ? bigDecimalValidator
                                                        .validate(valueToValidate, LocaleContextHolder.getLocale())
                                                        : null);
                                        break;
                                    case CURRENCY_INPUT:
                                        if (valueToValidate != null && !valueToValidate.isEmpty()) {
                                            CurrencyValidator currencyValidator = new CurrencyValidator(true, true);
                                            if (!currencyValidator.isValid(valueToValidate,
                                                    LocaleContextHolder.getLocale())) {
                                                errors.rejectValue(validationFieldName, "field_invalid_type",
                                                        "통화 형식이 올바르지 않습니다. 정수로 입력바랍니다.");
                                                continue;
                                            } else {
                                                questionAnswer.getBigDecimalAnswerValuesMatrix()[r - 1][c
                                                        - 1] = currencyValidator.validate(valueToValidate,
                                                                LocaleContextHolder.getLocale());
                                                questionAnswer.getStringAnswerValuesMatrix()[r - 1][c
                                                        - 1] = currencyValidator
                                                                .format(currencyValidator.validate(valueToValidate,
                                                                        LocaleContextHolder.getLocale()),
                                                                        LocaleContextHolder.getLocale());
                                            }
                                            // removing all '$' and ',' from string prior to validating max and min
                                            valueToValidate = valueToValidate.replaceAll("\\$", "");
                                            valueToValidate = valueToValidate.replaceAll(",", "");
                                        }
                                        break;
                                    case DATE_INPUT:
                                        if (valueToValidate != null && !valueToValidate.isEmpty()) {
                                            if (!GenericValidator.isDate(valueToValidate, dateFormat, true)) {
                                                errors.rejectValue(validationFieldName, "field_invalid_date",
                                                        "날짜 형식이 올바르지 않습니다. YYYY-MM-DD 형식으로 입력바랍니다.");
                                                continue;
                                            }
                                        }
                                        questionAnswer.getDateAnswerValuesMatrix()[r - 1][c
                                                - 1] = (valueToValidate.trim().length() > 0
                                                        ? DateValidator.getInstance().validate(valueToValidate)
                                                        : null);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    break;

                case STAR_RATING:
                    valueToValidate = questionAnswer.getStringAnswerValue();
                    // validate isRequired
                    if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                        errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                        break;
                    }
                    break;
                case SMILEY_FACES_RATING:
                    valueToValidate = questionAnswer.getStringAnswerValue();
                    // validate isRequired
                    if (question.getRequired() && GenericValidator.isBlankOrNull(valueToValidate)) {
                        errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                        break;
                    }
                    break;
                case IMAGE_DISPLAY:
                    // no validation
                    break;
                case VIDEO_DISPLAY:
                    // no validation
                    break;
                case FILE_UPLOAD:
                    // validate that the file is not null
                    if (question.getRequired() && (questionAnswer.getSurveyDocument() == null)
                            && !questionAnswer.getDocumentAlreadyUploded()) {
                        errors.rejectValue(validationFieldName, "field_required", "필수 항목입니다.");
                        break;
                    }

                    // check the file type from the file extension
                    if (questionAnswer.getSurveyDocument() != null
                            && !validateFileType(questionAnswer.getSurveyDocument().getContentType())) {
                        errors.rejectValue(validationFieldName, invalidContentMessage, this.invalidContentMessage);
                        break;
                    }

                    // validate the size
                    if (questionAnswer.getSurveyDocument() != null
                            && !((questionAnswer.getSurveyDocument().getContent().length) <= maximunFileSize
                                    * ONE_BYTE)) {
                        errors.rejectValue(validationFieldName, invalidFileSizeMessage, this.invalidFileSizeMessage);
                        break;
                    }

                    break;
                }

            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw (new RuntimeException(e));
        }
    }

    private boolean validateFileType(String fileType) {
        return Arrays.asList(this.validContentTypes.split(",")).contains(fileType);
    }

}