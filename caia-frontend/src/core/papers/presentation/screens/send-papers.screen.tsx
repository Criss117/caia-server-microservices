"use client";

import { useEffect } from "react";
import { Button } from "@/core/shared/components/ui/button";
import { Form, FormField } from "@/core/shared/components/ui/form";
import { Input } from "@/core/shared/components/ui/input";
import { Label } from "@/core/shared/components/ui/label";
import useSendPaper from "../../application/hooks/use.send-paper";
import FormItemInput from "@/core/shared/components/form/form-item-input";
import usePaperKeys from "../../application/hooks/use.paperkeys";
import ErrorMessage from "@/core/shared/components/form/error-message";
import { Badge } from "@/core/shared/components/ui/badge";

interface Props {
  slug: string;
}

const paperInfo = [
  {
    name: "title",
    label: "Titulo del paper",
    placeholder: "Titulo del paper",
    type: "text",
  },
  {
    name: "description",
    label: "Descripción del paper",
    placeholder: "Descripción del paper",
    type: "text",
  },
] as const;

const SendPapersScreen = ({ slug }: Props) => {
  const { key, keys, setKey, initialKeys } = usePaperKeys();

  const { form, onSubmit, setFile } = useSendPaper({ slug, keys });

  useEffect(() => {
    initialKeys(form.getValues("keys"));
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <Form {...form}>
      <form
        onSubmit={onSubmit}
        className="flex flex-col w-3/4 md:w-1/2 space-y-5 mx-auto"
      >
        <ErrorMessage error={form.formState.errors.root?.message} />
        <fieldset className="flex flex-col space-y-5 border p-5 rounded-lg shadow-md">
          <Label className="text-xl font-bold">Envia tu paper</Label>
          <Label htmlFor="conference-name" className="space-y-2">
            Nombre de la conferencia
            <Input
              type="text"
              id="conference-name"
              placeholder="Nombre de la conferencia"
              defaultValue={slug}
              disabled
            />
          </Label>
        </fieldset>
        <fieldset className="flex flex-col space-y-5 border p-5 rounded-lg shadow-md">
          <Label className="text-xl font-bold">Información del paper</Label>
          {paperInfo.map((item) => (
            <Label htmlFor={item.name} className="space-y-2" key={item.name}>
              {item.label}
              <FormField
                control={form.control}
                name={item.name}
                render={({ field }) => (
                  <FormItemInput field={field} {...item} />
                )}
              />
            </Label>
          ))}
        </fieldset>
        <fieldset className="flex flex-col space-y-5 border p-5 rounded-lg shadow-md">
          <Label htmlFor="keywords">
            Palabras Clave
            <div className="flex flex-wrap gap-x-2 my-2">
              {keys.map((key, index) => (
                <Badge key={index}>{key}</Badge>
              ))}
            </div>
            <Input
              type="text"
              id="keywords"
              onChange={(e) => setKey(e.target.value)}
              value={key || ""}
              placeholder="Ingresa las palabras clave separadas por comas o espacios"
            />
          </Label>
          <Label htmlFor="paper">
            Subir paper
            <Input
              type="file"
              id="paper"
              placeholder="Subir paper"
              onChange={(e) => setFile(e.target.files)}
            />
          </Label>
        </fieldset>
        <fieldset>
          <Button variant="default" className="w-full" type="submit">
            Enviar paper
          </Button>
        </fieldset>
      </form>
    </Form>
  );
};

export default SendPapersScreen;
