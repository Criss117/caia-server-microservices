import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/core/shared/components/ui/table";
import { format } from "@formkit/tempo";

const PapersAssignedTable = () => {
  return (
    <Table>
      <TableCaption>Lista de papers</TableCaption>
      <TableHeader>
        <TableRow>
          <TableHead className="w-[200px]">Nombre</TableHead>
          <TableHead className="">Conferencia</TableHead>
          <TableHead className="">Estatus</TableHead>
          <TableHead className="text-right">Autor</TableHead>
          <TableHead className="text-right">Keys</TableHead>
          <TableHead className="text-right">Última actualización</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        <TableRow>
          <TableCell>Paper 1</TableCell>
          <TableCell className="font-medium">Conferencia 1</TableCell>
          <TableCell>pendiente</TableCell>
          <TableCell className="text-right">Aristoteles, Carl Marx</TableCell>
          <TableCell className="text-right">key1, key2, key3</TableCell>
          <TableCell className="text-right">{format(new Date())}</TableCell>
        </TableRow>
      </TableBody>
      <TableBody>
        <TableRow>
          <TableCell>Paper 2</TableCell>
          <TableCell className="font-medium">Conferencia 2</TableCell>
          <TableCell>rechazado</TableCell>
          <TableCell className="text-right">Aristoteles, Carl Marx</TableCell>
          <TableCell className="text-right">key1, key2, key3</TableCell>
          <TableCell className="text-right">{format(new Date())}</TableCell>
        </TableRow>
      </TableBody>
    </Table>
  );
};

export default PapersAssignedTable;
