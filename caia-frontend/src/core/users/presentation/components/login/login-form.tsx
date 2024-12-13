"use client";

import { Loader } from "lucide-react";
import { useSearchParams } from "next/navigation";

import FormItemInput from "@/core/shared/components/form/form-item-input";
import { Button } from "@/core/shared/components/ui/button";
import { Form, FormField } from "@/core/shared/components/ui/form";
import useLogin from "@/core/users/application/hooks/use-login";
import ErrorMessage from "@/core/shared/components/form/error-message";

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

const LoginForm = () => {
  const params = useSearchParams();
  const error = params.get("error");
  const { form, pending, onSubmit } = useLogin();

  return (
    <Form {...form}>
      <form
        onSubmit={onSubmit}
        className="flex flex-col w-3/4 md:w-1/2 space-y-5 mt-10"
      >
        <ErrorMessage
          error={
            form.formState.errors.root?.message ||
            (error && "Credenciales inválidas")
          }
        />
        <fieldset>
          <FormField
            control={form.control}
            name={email.name}
            render={({ field }) => <FormItemInput field={field} {...email} />}
          />
        </fieldset>
        <fieldset>
          <FormField
            control={form.control}
            name={password.name}
            render={({ field }) => (
              <FormItemInput field={field} {...password} />
            )}
          />
        </fieldset>
        <fieldset className="w-full">
          <Button variant="default" className="w-full" type="submit">
            {pending ? (
              <Loader className="w-5 h-5 animate-spin" />
            ) : (
              "Iniciar sesión"
            )}
          </Button>
        </fieldset>
      </form>
    </Form>
  );
};

export default LoginForm;
