"use client";

import { Button } from "@/core/shared/components/ui/button";
import useSignUp from "@/core/users/application/hooks/use-signup";
import { Form, FormField } from "@/core/shared/components/ui/form";
import FormItemInput from "@/core/shared/components/form/form-item-input";
import ErrorMessage from "@/core/shared/components/form/error-message";

const formsItems = [
  {
    name: "firstName",
    label: "Nombres",
    placeholder: "Nombres",
    type: "text",
  },
  {
    name: "lastName",
    label: "Apellidos",
    placeholder: "Apellidos",
    type: "text",
  },
] as const;

const affiliation = {
  name: "affiliation",
  label: "Afiliación",
  placeholder: "Afiliación",
  type: "text",
} as const;

const email = {
  name: "email",
  label: "Correo",
  placeholder: "Correo",
  type: "email",
} as const;

const password = {
  name: "password",
  label: "Contraseña",
  placeholder: "Contraseña",
  type: "password",
} as const;

const SignUpForm = () => {
  const { form, onSubmit } = useSignUp();

  return (
    <Form {...form}>
      <form
        onSubmit={onSubmit}
        className="flex flex-col w-3/4 md:w-1/2 space-y-5 mt-10"
      >
        <ErrorMessage error={form.formState.errors.root?.message} />
        <fieldset className="flex gap-x-2">
          {formsItems.map((item) => (
            <FormField
              key={item.name}
              control={form.control}
              name={item.name}
              render={({ field }) => (
                <FormItemInput field={field} {...item} className="w-1/2" />
              )}
            />
          ))}
        </fieldset>
        <fieldset>
          <FormField
            key={affiliation.name}
            control={form.control}
            name={affiliation.name}
            render={({ field }) => (
              <FormItemInput field={field} {...affiliation} />
            )}
          />
        </fieldset>
        <fieldset>
          <FormField
            key={email.name}
            control={form.control}
            name={email.name}
            render={({ field }) => <FormItemInput field={field} {...email} />}
          />
        </fieldset>
        <fieldset>
          <FormField
            key={password.name}
            control={form.control}
            name={password.name}
            render={({ field }) => (
              <FormItemInput field={field} {...password} />
            )}
          />
        </fieldset>
        <fieldset className="w-full">
          <Button variant="default" className="w-full" type="submit">
            Crear cuenta
          </Button>
        </fieldset>
      </form>
    </Form>
  );
};

export default SignUpForm;
